package leaveManagement.backend.Controller;

import jakarta.validation.Valid;
import leaveManagement.backend.Entity.LeaveRequest;
import leaveManagement.backend.Service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaveRequests")
@CrossOrigin(origins = "https://localhost:5173")
public class LeaveRequestController {
    @Autowired
    private LeaveRequestService leaveRequestService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<LeaveRequest>> getAllRequests() {
        List<LeaveRequest> leaveRequests = leaveRequestService.getAllRequest();
        return ResponseEntity.ok(leaveRequests);
    }

    //USER and ADMIN both can post leave requests
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping
    public ResponseEntity<LeaveRequest> saveRequest(@Valid @RequestBody LeaveRequest leaveRequest) {
        LeaveRequest savedRequest = leaveRequestService.saveRequest(leaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRequest);
    }

    //ADMIN can update(approve/reject) requests
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<LeaveRequest> updateRequest(@PathVariable Long id, @Valid @RequestBody LeaveRequest leaveRequest) {
        leaveRequest.setId(id);
        LeaveRequest updatedRequest = leaveRequestService.updateRequest(leaveRequest);
        return ResponseEntity.ok(updatedRequest);
    }

    //ADMIN can delete requests
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        leaveRequestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }

    //USER can see only their own leave requests unless ADMIN
    @PreAuthorize("hasRole('ADMIN') or principal.username == @leaveRequestService.getEmployeeEmail(#employeeId)")
    @GetMapping("employee/{employeeId}")
    public ResponseEntity<List<LeaveRequest>> getAllRequestsByEmployee(@PathVariable Long employeeId) {
        List<LeaveRequest> leaveRequests = leaveRequestService.getAllRequestsByEmployee(employeeId);
        return ResponseEntity.ok(leaveRequests);
    }
}
