package ro.ubb.Services;

import ro.ubb.Model.Exceptions.ServiceExceptions.TrainServiceException;
import ro.ubb.Model.Train;
import ro.ubb.Repository.IRepository;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *
 */
public class TrainService  implements Service<Integer, Train>
{
    private static IRepository<Integer, Train> repository = null;

    /**
     * @param repository creates the instance setting a repository
     */
    public TrainService(IRepository<Integer, Train> repository)
    {

        TrainService.repository = repository;
    }

    /**
     * @param train the train to be addded
     */
    public void executeCreate(Train train) {
        try {
            repository.save(train);
        }catch (Exception runtimeException){
            throw new TrainServiceException("Something went wrong when trying to save an entry!");
        }
    }

    /**
     * @returns a set of all trains
     */
    public Set<Train> getAllEntities() {
        Iterable<Train> trains = repository.findAll();
        return StreamSupport.stream(trains.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     *
     * @param train updates the train in the repository
     */
    public void executeUpdate(Train train) {
        try {
            repository.update(train);
        }catch (Exception runtimeException){
            throw new TrainServiceException("Something went wrong when trying to update an entry!");
        }
    }

    /**
     *
     * @param id deletes an element with the specified id
     */
    public void executeDelete(Integer id) {
        try {
            repository.delete(id);
        }catch (Exception runtimeException){
            throw new TrainServiceException("Something went wrong when trying to delete an entry!");
        }
    }

    /**
     *
     * @param date date parameter to check the rest of trains
     * @returns the set of all trains that are fabricated before given date
     */
    public Set<Train> getTrainsFabricatedBefore(LocalDate date){
        return this.getAllEntities().stream()
                .filter(e-> e.getDate().compareTo(date) < 0)
                .collect(Collectors.toSet());
    }

    /**
     *
     * @param type type parameter for filtering entities
     * @returns the set of all trains with the specified type
     */
    public Set<Train> getTrainsWithSpecificTrainType(String type){
        return this.getAllEntities().stream()
                .filter(e-> e.getTrainType().equals(type))
                .collect(Collectors.toSet());
    }

    /**
     *
     * @returns a set containing all IDs
     */
    public static Set<Integer> getEntitiesIds() {
        return StreamSupport.stream(repository.findAll().spliterator(), true)
                .map(Train::getId)
                .collect(Collectors.toSet());
    }

    /**
     *
     * @returns a set containing all trains
     */
    public static Set<Train> getTrains(){
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
    }
}
