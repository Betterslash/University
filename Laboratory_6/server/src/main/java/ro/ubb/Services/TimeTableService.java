package ro.ubb.Services;

import org.springframework.stereotype.Service;
import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.TrainsStationsEntity;
@Service
public class TimeTableService extends ServiceImpl<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> {

}
