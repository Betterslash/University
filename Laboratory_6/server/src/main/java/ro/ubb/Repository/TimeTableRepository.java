package ro.ubb.Repository;

import org.springframework.stereotype.Repository;
import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.TrainsStationsEntity;
@Repository
public class TimeTableRepository extends CRUDRepository<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>>{

}
