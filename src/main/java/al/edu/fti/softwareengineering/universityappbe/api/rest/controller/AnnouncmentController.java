package al.edu.fti.softwareengineering.universityappbe.api.rest.controller;

import al.edu.fti.softwareengineering.universityappbe.api.rest.controller.common.CommonCrudRestController;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.commentableAndLikeable.CourseAnnouncmentDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.CourseAnnouncmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/announcment")
public class AnnouncmentController extends CommonCrudRestController<CourseAnnouncmentDTO, Long> {

    @GetMapping("/course/{idCourse}")
    public ResponseEntity<List<CourseAnnouncmentDTO>> getCourseAnnouncmentsOfACourse(@PathVariable("idCourse") Long idCourse) {
        return ResponseEntity.ok(getCourseAnnouncmentService().announcmentsOfACourse(idCourse));
    }

    private CourseAnnouncmentService getCourseAnnouncmentService() {
        return (CourseAnnouncmentService) service;
    }
}

