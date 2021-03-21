package ro.ubb.Services;


import ro.ubb.Model.Station;
import ro.ubb.Repository.IRepository;
import ro.ubb.Services.ServiceAbstractions.AbstractIService;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *
 */
public class StationService extends AbstractIService<Integer, Station> {

    public StationService(IRepository<Integer, Station> repository)
    {
        super(repository);
    }
    /**
     *
     * @returns a set of all stations
     */
    public Set<Station> getAllEntities() {
        Iterable<Station> stations = repository.findAll();
        return StreamSupport.stream(stations.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     *
     * @returns a set containing all IDs
     */
    public Set<Integer> getEntitiesIds() {
        return StreamSupport.stream(super.repository.findAll().spliterator(), false)
                .map(Station::getId)
                .collect(Collectors.toSet());
    }

    /**
     *
     * @returns a set containing all stations
     */
    public Set<Station> getStations()
    {
        return StreamSupport.stream(super.repository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
    }
}
