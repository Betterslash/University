package Repository;

import Model.PrgState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{
    ArrayList<PrgState> listOfPrograms;
    String filePath;

    public Repository(String filePath){
        this.filePath = filePath;
        listOfPrograms = new ArrayList<>();
    }
    @Override
    public void addToRepository(PrgState state) {
        this.listOfPrograms.add(state);
    }
    @Override
    public PrgState getPrgState() {
        return this.listOfPrograms.get(this.listOfPrograms.size() - 1);
    }

    @Override
    public void logProgState(PrgState state) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.filePath, true));
        bufferedWriter.write(state.toString());
        bufferedWriter.close();
    }

    @Override
    public List<PrgState> getPrgList() {
        return this.listOfPrograms;
    }

    @Override
    public void setProgramList(List<PrgState> representation) {
        this.listOfPrograms = (ArrayList<PrgState>) representation;
    }


}
