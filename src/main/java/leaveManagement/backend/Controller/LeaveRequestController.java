package leaveManagement.backend.Controller;

import jakarta.validation.Valid;
import leaveManagement.backend.Entity.LeaveRequest;
import leaveManagement.backend.Service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaveRequests")
@CrossOrigin(origins = "https://localhost:5173")
public class LeaveRequestController {
    @Autowired
    private LeaveRequestService leaveRequestService;

    @GetMapping
    public List<LeaveRequest> getAllRequests(){
        return leaveRequestService.getAllRequest();
    }

    @PostMapping
    public LeaveRequest saveRequest(@Valid @RequestBody LeaveRequest leaveRequest){
        return leaveRequestService.saveRequest(leaveRequest);
    }

    @PutMapping("/{id}")
    public LeaveRequest updateRequest(@PathVariable Long id,@Valid @RequestBody LeaveRequest leaveRequest){
        leaveRequest.setId(id);
        return leaveRequestService.updateRequest(leaveRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable Long id){
        leaveRequestService.deleteRequest(id);
    }

    @GetMapping("employee/{employeeId}")
    public List<LeaveRequest> getAllRequestsByEmployee(@PathVariable Long employeeId){
        return leaveRequestService.getAllRequestsByEmployee(employeeId);
    }
}
