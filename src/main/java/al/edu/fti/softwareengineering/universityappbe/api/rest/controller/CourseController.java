package al.edu.fti.softwareengineering.universityappbe.api.rest.controller;

import al.edu.fti.softwareengineering.universityappbe.api.rest.controller.common.CommonCrudRestController;
import al.edu.fti.softwareengineering.universityappbe.api.security.userDetails.MyUserDetails;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.commentableAndLikeable.CourseDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController extends CommonCrudRestController<CourseDTO, Long> {

    @GetMapping("/available/{page}")
    public ResponseEntity<List<CourseDTO>> getAvailableCourses(@PathVariable("page") int pageNumber) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(getCourseService().findAllCoursesAvailableForLoggedUser(userDetails.getId(), pageNumber));
    }

    @GetMapping("/enrolled/{page}")
    public ResponseEntity<List<CourseDTO>> getEnrolledCourses(@PathVariable("page") int pageNumber) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(getCourseService().findAllByStudentEnrolled_id(userDetails.getId(), pageNumber));
    }

    @PutMapping("/enroll")
    public ResponseEntity<Void> enrollInCourse(@RequestBody Long idCourse) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        getCourseService().saveUser(idCourse, userDetails.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/drop/{idCourse}")
    public ResponseEntity<Void> dropCourse(@PathVariable("idCourse") Long idCourse) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.getCourseService().dropUser(idCourse, userDetails.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/friend/{idFriend}/{pageNumber}")
    public ResponseEntity<List<CourseDTO>> getCoursesOfAFriend(@PathVariable("idFriend") Long idFriend, @PathVariable("pageNumber") int pageNumber) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(getCourseService().getCoursesOfAFriend(userDetails.getId(), idFriend, pageNumber));
    }

    private CourseService getCourseService() {
        return (CourseService) service;
    }
}
