package al.edu.fti.softwareengineering.universityappbe.api.rest.controller;

import al.edu.fti.softwareengineering.universityappbe.api.rest.controller.common.CommonCrudRestController;
import al.edu.fti.softwareengineering.universityappbe.api.security.userDetails.MyUserDetails;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.CommentBodyDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.userInteractions.CommentDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController extends CommonCrudRestController<CommentDTO, Long> {

    @PostMapping("/course/{idCourse}")
    public ResponseEntity<Void> addCommentInACourse(@PathVariable("idCourse") Long idCourse, @RequestBody CommentBodyDTO commentBodyDTO) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.getCommentService().addCommentToACourse(idCourse, commentBodyDTO.getContent(), userDetails.getId());
        return ResponseEntity.ok().build();
    }

    private CommentService getCommentService() {
        return (CommentService) service;
    }
}
