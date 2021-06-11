package al.edu.fti.softwareengineering.universityappbe.api.rest.controller;

import al.edu.fti.softwareengineering.universityappbe.api.rest.controller.common.CommonCrudRestController;
import al.edu.fti.softwareengineering.universityappbe.api.security.userDetails.MyUserDetails;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.commentableAndLikeable.CourseDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController extends CommonCrudRestController<CourseDTO, Long> {

    @GetMapping("/available/{page}")
    public ResponseEntity<List<CourseDTO>> getAvailableCourses(@PathVariable("page") int pageNumber) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(((CourseService) service).findAllCoursesAvailableForLoggedUser(userDetails.getId(), pageNumber));
    }

    @GetMapping("/enrolled/{page}")
    public ResponseEntity<List<CourseDTO>> getEnrolledCourses(@PathVariable("page") int pageNumber) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(((CourseService) service).findAllByStudentEnrolled_id(userDetails.getId(), pageNumber));
    }
}
