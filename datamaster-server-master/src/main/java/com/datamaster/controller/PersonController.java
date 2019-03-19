package com.datamaster.controller;

import com.datamaster.model.Person;
import com.datamaster.service.PersonServiceImpl;
import com.datamaster.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PersonController {

    @Autowired
    private PersonServiceImpl personService;

    @RequestMapping(value="/admin/patientAdd")
    @ResponseBody

    public Response delPerson(HttpServletRequest request){
        String name=request.getParameter("name");
        String age=request.getParameter("age");
        String medicalRecordNum=request.getParameter("medicalRecordNum");
        String sex=request.getParameter("sex");
        String diagnosis=request.getParameter("diagnosis");
        String phone=request.getParameter("phone");
        String eduLevel=request.getParameter("eduLevel");
        System.out.println(name);
        System.out.println(age);
        System.out.println(medicalRecordNum);
        System.out.println(diagnosis);
        Person person=new Person();
        person.setName(name);
        person.setAge(Integer.parseInt(age));
        person.setMedicalRecordNum(medicalRecordNum);
        person.setSex(sex);
        person.setDiagnosis(diagnosis);
        person.setPhone(phone);
        person.setEduLevel(eduLevel);
       // System.out.println(personService.save(person).toString());
        if (personService.save(person) != null) {
            return new Response(0, "添加成功");
        } else {
            return new Response(1, "添加失败");
        }
    }
}
