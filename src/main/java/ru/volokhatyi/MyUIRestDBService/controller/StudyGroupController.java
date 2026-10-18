package ru.volokhatyi.MyUIRestDBService.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.volokhatyi.MyUIRestDBService.dao.StudyGroupRepository;
import ru.volokhatyi.MyUIRestDBService.entity.StudyGroup;

import java.util.Optional;

@Slf4j
@RestController
public class StudyGroupController {

    private final StudyGroupRepository studyGroupRepository;

    @Autowired
    public StudyGroupController(StudyGroupRepository studyGroupRepository) {
        this.studyGroupRepository = studyGroupRepository;
    }

    @GetMapping("/groups")
    public ModelAndView getAllGroups() {
        ModelAndView mav = new ModelAndView("list-groups");
        mav.addObject("groups", studyGroupRepository.findAll());
        return mav;
    }

    @GetMapping("/addGroupForm")
    public ModelAndView addGroupForm() {
        ModelAndView mav = new ModelAndView("add-group-form");
        StudyGroup group = new StudyGroup();
        mav.addObject("group", group);
        return mav;
    }

    @PostMapping("/saveGroup")
    public RedirectView saveStudent(@ModelAttribute StudyGroup studyGroup) {
        studyGroupRepository.save(studyGroup);
        return new RedirectView("groups");
    }

    @GetMapping("/showUpdateGroupForm")
    public ModelAndView showUpdateForm(@RequestParam Long studyGroupId) {
        ModelAndView mav = new ModelAndView("add-group-form");
        Optional<StudyGroup> optionalStudyGroup = studyGroupRepository.findById(studyGroupId);
        StudyGroup studyGroup = new StudyGroup();
        if (optionalStudyGroup.isPresent()) {
            studyGroup = optionalStudyGroup.get();
        }
        mav.addObject("group", studyGroup);
        return mav;
    }

    @GetMapping("/deleteGroup")
    public RedirectView deleteGroup(@RequestParam Long groupId) {
        studyGroupRepository.deleteById(groupId);
        return new RedirectView("groups");
    }
}
