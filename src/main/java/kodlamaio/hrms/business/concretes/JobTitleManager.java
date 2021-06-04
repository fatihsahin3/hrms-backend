package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobTitleService;
import kodlamaio.hrms.business.constraints.Messages.ResponseMessages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobTitleDao;
import kodlamaio.hrms.entities.concretes.JobTitle;

@Service
public class JobTitleManager implements JobTitleService {
	
	private JobTitleDao jobTitleDao;

	@Autowired
	public JobTitleManager(JobTitleDao jobTitleDao) {
		super();
		this.jobTitleDao = jobTitleDao;
	}

	@Override
	public DataResult<List<JobTitle>> getAll() {
		return new DataResult<List<JobTitle>>(this.jobTitleDao.findAll(), true, ResponseMessages.JobTitlesListed);
	}

	@Override
	public DataResult<JobTitle> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result add(JobTitle jobTitle) {
		
		if(checkIfJobTitleAlreadyExists(jobTitle)) {
			return new ErrorResult("This job title already exists!");
		}
		
		jobTitleDao.save(jobTitle);
		return new SuccessResult("Job Title " + jobTitle.getJobTitle() + " has been successfully added!");
	}

	@Override
	public Result update(JobTitle jobTitle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delete(JobTitle jobTitle) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean checkIfJobTitleAlreadyExists(JobTitle jobTitle) {
		
		if(jobTitleDao.findByJobTitle(jobTitle.getJobTitle()) == null ) {
			
			return false;			
		}		
		
		return true;
	}

}
