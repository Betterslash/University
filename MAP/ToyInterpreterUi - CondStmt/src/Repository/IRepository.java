package Repository;

import Model.PrgState;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    void addToRepository(PrgState state);
    PrgState getPrgState();
    void logProgState(PrgState prgState) throws IOException;
    List<PrgState> getPrgList();
    void setProgramList(List<PrgState> representation);
}
