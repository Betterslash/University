package Controller;

import Model.PrgState;
import Model.Values.RefValue;
import Model.Values.Value;
import Model.except.MyException;
import Repository.IRepository;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class Controller {
    public IRepository getRepository() {
        return repository;
    }

    IRepository repository;
    ExecutorService executor;
    List<Integer> getListOfAdresses(Collection<Value> symTable, Collection<Value> heapTable){
        Collection<Integer> heapCollection = heapTable.stream()
        .filter(e -> e instanceof RefValue)
                .map( v -> ((RefValue) v).getAddress())
                .collect(Collectors.toList());
        Collection<Integer> symCollection = symTable.stream()
                .filter(v -> v instanceof RefValue)
                .map(e -> ((RefValue) e).getAddress())
                .collect(Collectors.toList());
        symCollection.addAll(heapCollection);
        return new ArrayList<>(symCollection);
    }
    private Map<Integer, Value> garbageCollector(List<Integer> newSymTable, Map<Integer, Value> heap){
        return heap.entrySet()
                .stream()
                .filter(e -> newSymTable.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public Controller(IRepository repository){
        this.repository = repository;
    }
    public void add(PrgState prgState){
        this.repository.addToRepository(prgState);
    }

    public void oneStepForAllPrg(List<PrgState> prgStateList) throws InterruptedException, MyException {
        prgStateList.forEach(p -> {
            try {
                repository.logProgState(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        List<Callable<PrgState>> callList = prgStateList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(p::oneStep))
                .collect(Collectors.toList());
        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        System.out.println(e.getMessage());
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        prgStateList.addAll(newPrgList);
        prgStateList.forEach(pr -> {
            try {
                this.repository.logProgState(pr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public void allStep() throws InterruptedException, MyException {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgStateList = removeCompleted(repository.getPrgList());
        //while (prgStateList.size() > 0){
            prgStateList
               .forEach(e ->{
                   List<Integer> newSymTable = this.getListOfAdresses(e.getTopOfSymStack().getRepresentation().values(), e.getHeapTable().getRepresentation().values());
                   Map<Integer, Value> newHeap = garbageCollector(newSymTable, e.getHeapTable().getRepresentation());
                   e.setHeapTable(newHeap);
               });
            oneStepForAllPrg(prgStateList);

        //}
        executor.shutdownNow();
        repository.setProgramList(prgStateList);
    }
    public List<PrgState> removeCompleted(List<PrgState> prgStateList){
        return prgStateList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        List<PrgState> prgStateList = repository.getPrgList();
        if(prgStateList.size() > 0){
            return prgStateList.get(0).getGlobalTable() +"\n"+ prgStateList.get(0).getExeStack().toString();
        }
            return "Finished";
    }
}
