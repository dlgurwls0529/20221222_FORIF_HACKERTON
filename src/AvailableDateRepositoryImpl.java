import java.util.HashMap;
import java.util.List;

public class AvailableDateRepositoryImpl implements AvailableDateRepository{
    @Override
    public boolean save(int rId, int uId, int date) {
        return false;
    }

    @Override
    public List<Integer> findManyDateByRIdAndUId(int rId, int uId) {
        return null;
    }

    @Override
    public boolean deleteByRIdAndUId(int rId, int uId) {
        return false;
    }

    @Override
    public List<HashMap<Integer, String>> findManyPairByRoomName(String roomName) {
        return null;
    }

    @Override
    public List<HashMap<Integer, Integer>> findManyPairByRId(int rId) {
        return null;
    }
}
