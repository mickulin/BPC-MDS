package mds.uvod;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping
public class StudentsWork
{

    @GetMapping("student")
    public String returnName(@RequestParam(defaultValue = "Petr Mička")String name, @RequestParam(defaultValue = "230132") String id)
    {
        return "Student: <b>" + name + "</b> ID: <b>"+ id +"</b>";
    }

    
    
    @GetMapping("students")
    public String returnList(@RequestParam(required = false) Integer vutid)
    {
        if (vutid == null)
        {
            Student.addStudents();
            StringBuilder listStudentu = new StringBuilder();
            for(Student stud : Student.students)
            {
                listStudentu.append(stud.toString()).append("<br>");
            }
            return listStudentu.toString();
        }

        Optional<Student> result = Student.students.stream().filter(item -> item.returnID() == vutid).findFirst();
        if (result.isEmpty())
        {
            return "ID: " + vutid + " neni v databazi";
        }
        else
        {
            return result.get().toString();
        }



    }



}
