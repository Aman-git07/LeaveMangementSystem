package leaveManagement.backend.Service;

import jakarta.validation.Valid;
import leaveManagement.backend.Entity.LeaveRequest;
import leaveManagement.backend.Repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRequestService {
    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    public List<LeaveRequest> getAllRequest(){return leaveRequestRepository.findAll();}

    public LeaveRequest saveRequest(@Valid LeaveRequest leaveRequest){return leaveRequestRepository.save(leaveRequest);}

    public LeaveRequest updateRequest(@Valid LeaveRequest leaveRequest){return leaveRequestRepository.save(leaveRequest);}

    public void deleteRequest(Long id){leaveRequestRepository.deleteById(id);}

    public List<LeaveRequest> getAllRequestsByEmployee(Long id){return leaveRequestRepository.findByEmployeeId(id);}
}
