package com.cmpe275.Lab2.controller;
import com.cmpe275.Lab2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.cmpe275.Lab2.model.response.SuccessResponseDto;
import org.jetbrains.annotations.NotNull;

@RestController
public class CollaboratorController {

    private final EmployeeService employeeService;


    @Autowired
    public CollaboratorController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /***
     * Creates collaboration between the two employees with the given IDs
     * @param employeeId1 Employee ID for creating collaboration
     * @param employeeId2 Employee ID for creating collaboration
     * @return 200 Success message after successful collaboration creation
     *         404 If employee does not exist
     */

    @PutMapping(value = "collaboration/{employeeId1}/{employeeId2}/{employerId1}/{employerId2}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public SuccessResponseDto createCollaboration(@PathVariable @NotNull Long employeeId1,
                                                  @PathVariable @NotNull Long employeeId2,
                                                  @PathVariable @NotNull String employerId1,
                                                  @PathVariable @NotNull String employerId2
    ) {
        employeeService.createCollaboration(
                employeeId1, employerId1, employeeId2, employerId2
        );
        System.out.println("Done!");
        System.out.println(employeeService.findEmployee(employeeId1, employerId1).toString());
        return new SuccessResponseDto("Collaboration created successfully");
    }

    /***
     * Removes collaboration between the two employees with the given IDs
     * @param employeeId1 Employee ID for removing collaboration
     * @param employeeId2 Employee ID for removing collaboration
     * @return 200 Success message after successful collaboration removal
     *         404 If employee does not exist
     */
    @DeleteMapping(value = "/removeCollaboration/{employeeId1}/{employeeId2}/{employerId1}/{employerId2}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public SuccessResponseDto deleteEmployee(@PathVariable @NotNull Long employeeId1,
                                             @PathVariable @NotNull Long employeeId2,
                                             @PathVariable @NotNull String employerId1,
                                             @PathVariable @NotNull String employerId2
    ) {
        employeeService.deleteCollaboration(
                employeeId1, employerId1, employeeId2, employerId2
        );
        return new SuccessResponseDto("Collaboration deleted successfully");
    }

}



