package com.example.SpringBootCacheIntegrationWithRedis.service;

import com.example.SpringBootCacheIntegrationWithRedis.model.EmployeeDetails;
import com.example.SpringBootCacheIntegrationWithRedis.model.UserDetails;
import com.example.SpringBootCacheIntegrationWithRedis.repository.EmployeeDetailsRepository;
import com.example.SpringBootCacheIntegrationWithRedis.repository.UserDetailRepository;
import com.example.SpringBootCacheIntegrationWithRedis.requestDTO.UserDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class LogicServiceImpl {

    @Autowired
    private EmployeeDetailsRepository employeeDetailsRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    public String saveEmployeeDetails(EmployeeDetails employeeDetails) throws Exception {
        EmployeeDetails details= employeeDetailsRepository.save(employeeDetails);
        if (details.getId() !=null){
            return "Success";
        }else throw new Exception("Something went wrong.!");
    }

    @Transactional
 /*   @CachePut(value = "userDetails",key = "allUserDetails")*/
    public UserDetails saveUserDetails(UserDTO userDTO) throws Exception {

        UserDetails userDetails =new UserDetails();

        if (userDTO.getEmail()!=null){
            userDetails.setFirstName(userDTO.getFirstName());
            userDetails.setLastName(userDTO.getLastName());
            userDetails.setEmail(userDTO.getEmail());
            userDetailRepository.save(userDetails);
        }else throw new Exception("Bad Request.!");

        return userDetails;
    }


    public List<UserDetails> getAllUsers() {
       return userDetailRepository.findAllByIsActiveAndDeletedFlagOrderByIdDesc(true,false);
    }

    public String deleteUser(long id) {
        Optional<UserDetails> userDetails = userDetailRepository.findById(id);
        if (userDetails.isPresent()) {
            userDetails.get().setActive(false);
            userDetails.get().setDeletedFlag(true);
            userDetailRepository.save(userDetails.get());
        }
        return "user deleted successfully.";
    }

    public UserDetails updateUser(UserDTO userDTO) throws Exception {

        Optional<UserDetails> userDetails = userDetailRepository.findByIsActiveAndDeletedFlagAndId(true,false,userDTO.getId());
        if (userDetails.isPresent()){
            userDetails.get().setFirstName(userDTO.getFirstName());
            userDetails.get().setLastName(userDTO.getLastName());
            userDetails.get().setEmail(userDTO.getEmail());
            userDetailRepository.save(userDetails.get());
            return userDetails.get();
        }else throw new Exception("User detail not found");

    }

//    public int lengthOfLongestSubstring(String s) {
//        int length = 0;
//        char[] arr = s.toCharArray();
//        for(int i=0; i< arr.length; i++){
//            for (int j=0; j< arr.length;j++){
//                if (i!=j && arr[i]!=arr[j]){
//                    length++;
//                }
//            }
//        }
//      return length;
//    }
    public int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        int start = 0;
        int end = 0;
        int maxLength = 0;
        Set<Character> uniqueChars = new HashSet<>();

        while (end < n) {
            char currentChar = s.charAt(end);
            if (uniqueChars.contains(currentChar)) {
                uniqueChars.remove(s.charAt(start));
                start++;
            } else {
                uniqueChars.add(currentChar);
                int length = end - start + 1;
                maxLength = Math.max(maxLength, length);
                end++;
            }
        }

        return maxLength;
    }
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        Map<Character, Integer> charMap = new HashMap<>();

        for (int start = 0, end = 0; end < n; end++) {
            char currentChar = s.charAt(end);
            if (charMap.containsKey(currentChar)) {
                start = Math.max(start, charMap.get(currentChar) + 1);
            }
            charMap.put(currentChar, end);
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }

}
