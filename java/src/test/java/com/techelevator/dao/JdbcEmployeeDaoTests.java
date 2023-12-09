package com.techelevator.dao;

import com.techelevator.model.User;
import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


public class JdbcEmployeeDaoTests extends BaseDaoTests {

    private JdbcEmployeeDao dao;
    private User testUser;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcEmployeeDao(jdbcTemplate);
    }

    // TODO write a test that checks getEmployeeIdFromUser(User user)
    //
    @Before
    public void setUp() {
        testUser = new User();
        testUser.setUsername("testUser");
    }

    //TODO test getEmployeeFromUser method
}

//        @Test
//        public void testGetEmployeeIdFromUser() {
//            // Mocking behavior of jdbcTemplate
//            SqlRowSet mockRowSet = mock(SqlRowSet.class);
//            when(jdbcTemplate.queryForRowSet(anyString(), eq(testUser.getUsername())))
//                    .thenReturn(mockRowSet);
//
//            // Mocking result set
//            when(mockRowSet.next()).thenReturn(true);
//            when(mockRowSet.getInt("employee_id")).thenReturn(42);
//
//            // Call the method to test
//            int result = yourClass.getEmployeeIdFromUser(testUser);
//
//            // Verify interactions
//            verify(jdbcTemplate).queryForRowSet(anyString(), eq(testUser.getUsername()));
//            verify(mockRowSet).next();
//            verify(mockRowSet).getInt("employee_id");
//
//            // Assert the result
//            assertEquals(42, result);
//        }
//
//        @Test(expected = DaoException.class)
//        public void testGetEmployeeIdFromUserException() {
//            // Mocking behavior of jdbcTemplate to throw an exception
//            when(jdbcTemplate.queryForRowSet(anyString(), eq(testUser.getUsername())))
//                    .thenThrow(new CannotGetJdbcConnectionException("Test exception"));
//
//            // Call the method to test and expect an exception
//            yourClass.getEmployeeIdFromUser(testUser);
//        }
//    }
//
//
//
//}
