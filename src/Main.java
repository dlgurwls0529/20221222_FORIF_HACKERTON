import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);

        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        System.out.println(userRepository.findManyByUIdList(arrayList));
    }
}
