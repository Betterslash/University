package ro.ubb.Services;


import ro.ubb.Model.Station;
import ro.ubb.Repository.IRepository;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *
 */
public class StationService implements Service<Integer, Station>
{
    private static IRepository<Integer, Station> repository;

    public StationService(IRepository<Integer, Station> repository)
    {
        StationService.repository = repository;
    }

    /**
     *
     * @param station adds a station in the repository
     * @throws IOException in case file reading goes wrong
     */
    public void executeCreate(Station station) throws IOException {
        repository.save(station);
    }

    /**
     *
     * @param object updates an object in the repository
     * @throws IOException in case file reading goes wrong
     */
    @Override
    public void executeUpdate(Station object) throws IOException {
        repository.update(object);
    }

    /**
     *
     * @param id deletes an element with the specified id
     * @throws IOException in case file reading goes wrong
     */
    @Override
    public void executeDelete(Integer id) throws IOException {
        repository.delete(id);
    }

    /**
     *
     * @return a set of all stations
     */
    public Set<Station> getAllEntities() {
        Iterable<Station> stations = repository.findAll();
        return StreamSupport.stream(stations.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     *
     * @return a set containing all IDs
     */
    public static Set<Integer> getEntitiesIds() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(Station::getId)
                .collect(Collectors.toSet());
    }
}
