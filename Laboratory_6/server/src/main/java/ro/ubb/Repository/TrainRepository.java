package ro.ubb.Repository;

import org.springframework.stereotype.Repository;
import ro.ubb.Model.Train;

@Repository
public class TrainRepository extends CRUDRepository<Integer, Train> {
}
