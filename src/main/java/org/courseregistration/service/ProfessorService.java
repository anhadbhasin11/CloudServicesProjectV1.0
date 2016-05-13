package org.courseregistration.service;

import org.courseregistration.exception.ApplicationException;
import org.courseregistration.model.Professor;
import org.courseregistration.dao.ProfessorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;
import org.courseregistration.exception.ApplicationException;
import org.courseregistration.dao.*;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;

@Service
public class ProfessorService {
    @Autowired

    private ProfessorDAO professorDAO;

    /**  Get details of a specific professor
     *
     * @return details of professor
     */
    public List<Professor> findAllProfessors(){
        List<Professor>  professors = professorDAO.findAll();
        List<Professor> toReturn = newArrayList();
        for(Professor professor:professors)
        {
             toReturn.add(professor);
        }
     return toReturn;
    }

    public Professor findProfessorById(Long id) throws ApplicationException {
        checkNotNull(id,"Id should not be null");
        Professor toReturn = professorDAO.findById(id);

        if(toReturn == null){
            throw ApplicationException.createNew()
                .withCode(404)
                .withTitle("Resource not found")
                .withStatus(Response.Status.NOT_FOUND.getStatusCode())
                .withDetail("Professor with id " + id + " is not available").build();
        }

        return toReturn;
    }

    public boolean addProfessor(Professor professor)throws ApplicationException{
        try {
            professorDAO.save(professor);
            return true;
        } catch(Exception e){
            throw ApplicationException
                .createNew()
                .withCode(Response.Status.PARTIAL_CONTENT.getStatusCode())
                .withTitle("Professor addition failed.")
                .withStatus(Response.Status.PARTIAL_CONTENT.getStatusCode())
                .withDetail("Can not add professor entry. Please check professor object")
                .build();
        }
    }

    public void addProfessors(List<Professor> professors) throws ApplicationException  {
        professorDAO.save(professors);
    }

    public void deleteProfessorById(Long id) throws ApplicationException{
       try{
           checkNotNull(id,"ID should not be null");
           professorDAO.delete(id);
       }catch (Exception e) {
           throw ApplicationException.createNew()
               .withCode(Response.Status.NOT_FOUND.getStatusCode())
               .withTitle("No Professor found in database")
               .withStatus(Response.Status.BAD_REQUEST.getStatusCode())
               .withDetail("Can not delete.Please check professor object")
               .build();
           }
    }

    public void deleteAllProfessors(Collection<Long> ids)throws ApplicationException{
        professorDAO.delete(ids);
    }

    public boolean updateProfessor(Long id ,Professor current)
        throws ApplicationException{
        checkNotNull(id,"Id should not be null");
        Professor toReturn =professorDAO.findById(id);
        if (toReturn != null){
            toReturn.setYearsOfExperience(current.getYearsOfExperience());
            toReturn.setAddress(current.getAddress());
            //toReturn.setHashedPassword(current.getHashedPassword());
            toReturn.setLastName(current.getLastName());
            toReturn.setOfficeHoursToTime(current.getOfficeHoursToTime());
            toReturn.setOfficeHoursFromTime(current.getOfficeHoursFromTime());
            toReturn.setMiddleName(current.getMiddleName());
            toReturn.setPhoneNumber(current.getPhoneNumber());
            toReturn.setFacultyType(current.getFacultyType());
            toReturn.setDateOfBirth(current.getDateOfBirth());
            toReturn.setFirstName(current.getFirstName());
            toReturn.setUsername(current.getUsername());
            professorDAO.update(toReturn);
            return true;
        }else{
            throw ApplicationException.createNew()
                .withCode(Response.Status.NOT_FOUND.getStatusCode())
                .withTitle("No Professor entry found in database")
                .withStatus(Response.Status.BAD_REQUEST.getStatusCode())
                .withDetail("Can not update. Please check professor object.")
                .build();
            }
    }

}





















