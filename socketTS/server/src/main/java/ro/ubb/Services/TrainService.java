package ro.ubb.Services;

import ro.ubb.Model.Train;
import ro.ubb.Repository.IRepository;
import ro.ubb.Services.ServiceAbstractions.AbstractIService;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *
 */
public class TrainService  extends AbstractIService<Integer, Train>
{
    public TrainService(IRepository<Integer, Train> repository) {
        super(repository);
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
    public Set<Integer> getEntitiesIds() {
        return StreamSupport.stream(this.repository.findAll().spliterator(), true)
                .map(Train::getId)
                .collect(Collectors.toSet());
    }

    /**
     *
     * @returns a set containing all trains
     */
    public Set<Train> getTrains(){
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
    }
}
