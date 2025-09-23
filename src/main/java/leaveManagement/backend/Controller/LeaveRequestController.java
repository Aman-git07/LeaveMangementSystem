package leaveManagement.backend.Controller;

import jakarta.validation.Valid;
import leaveManagement.backend.Entity.LeaveRequest;
import leaveManagement.backend.Service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaveRequests")
@CrossOrigin(origins = "https://localhost:5173")
public class LeaveRequestController {
    @Autowired
    private LeaveRequestService leaveRequestService;

    @GetMapping
    public ResponseEntity<List<LeaveRequest>> getAllRequests() {
        List<LeaveRequest> leaveRequests = leaveRequestService.getAllRequest();
        return ResponseEntity.ok(leaveRequests);
    }

    @PostMapping
    public ResponseEntity<LeaveRequest> saveRequest(@Valid @RequestBody LeaveRequest leaveRequest) {
        LeaveRequest savedRequest = leaveRequestService.saveRequest(leaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveRequest> updateRequest(@PathVariable Long id, @Valid @RequestBody LeaveRequest leaveRequest) {
        leaveRequest.setId(id);
        LeaveRequest updatedRequest = leaveRequestService.updateRequest(leaveRequest);
        return ResponseEntity.ok(updatedRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        leaveRequestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("employee/{employeeId}")
    public ResponseEntity<List<LeaveRequest>> getAllRequestsByEmployee(@PathVariable Long employeeId) {
        List<LeaveRequest> leaveRequests = leaveRequestService.getAllRequestsByEmployee(employeeId);
        return ResponseEntity.ok(leaveRequests);
    }
}
