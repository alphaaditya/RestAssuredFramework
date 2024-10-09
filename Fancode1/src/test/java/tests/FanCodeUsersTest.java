package tests;



import pojo.Todo;
import pojo.User;
import util.ApiUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class FanCodeUsersTest {
	
    @Test
    public void validateFanCodeUsersHaveMoreThan50PercentTasksCompleted() {
       
        List<User> users = ApiUtils.fetchUsers();

        List<User> fancodeUsers = users.stream()
            .filter(user -> {
                double lat = user.getAddress().getGeo().getLat();
                double lng = user.getAddress().getGeo().getLng();
                return lat > -40 && lat < 5 && lng > 5 && lng < 100;
            })
            .collect(Collectors.toList());

        
        for (User user : fancodeUsers) {
            List<Todo> todos = ApiUtils.fetchUserTodos(user.getId());

            long totalTasks = todos.size();
            long completedTasks = todos.stream().filter(Todo::isCompleted).count();

           
            double completedPercentage = (double) completedTasks / totalTasks * 100;
            
          //  System.out.println("User " + user.getName() + " has greater than 50% tasks completed.");
            
            Assert.assertTrue(completedPercentage > 50, "User " + user.getName() + " has less than 50% tasks completed.");
        }
    }
}
