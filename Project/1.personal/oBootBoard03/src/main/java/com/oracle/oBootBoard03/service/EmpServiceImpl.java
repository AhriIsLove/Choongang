package com.oracle.oBootBoard03.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootBoard03.domain.Emp;
import com.oracle.oBootBoard03.dto.EmpDto;
import com.oracle.oBootBoard03.repository.EmpRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class EmpServiceImpl implements EmpService {
	
	private final  EmpRepository empRepository;

	@Override
	public int totalEmp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EmpDto> empList(EmpDto empDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public int register(EmpDto empDto) {
		Emp emp = dtoToEntity(empDto);
		System.out.println("EmpServiceImpl register emp->"+emp);
		Emp saveEmp = empRepository.empSave(emp);
		return saveEmp.getEmp_no();
	}

	private Emp dtoToEntity(EmpDto empDto) {
		// Build 사용이유 : 유지보수 용이 
		// 1. 많은 필드(특히 선택적 필드 많을 때)
		// 2. 객체 생성 과정이 복잡할 때
		// 3. 가독성(불변 객채)
		// 간단 객체 --> 생성자
		Emp emp = Emp.builder()
					 .emp_id(empDto.getEmp_id())
					 .emp_password(empDto.getEmp_password())
					 .emp_name(empDto.getEmp_name())
					 .email(empDto.getEmail())
					 .emp_tel(empDto.getEmp_tel())
					 .sal(empDto.getSal())
					 .del_status(empDto.getDel_status())
					 .dept_code(empDto.getDept_code())
					 .emp_level(empDto.getEmp_level())
					 .in_date(empDto.getIn_date())
					 .build()
				  ;
		
		// 업로드 처리가 끝난 파일들의 이름 리스트 
		List<String> uploadfileNames = empDto.getUploadFileNames();
		System.out.println("EmpServiceImpl register uploadfileNames->"+uploadfileNames);

		if(uploadfileNames == null || uploadfileNames.isEmpty())  return emp;
		
		// Entity에게 uploadName명 넘겨줌  --> List<Emp> imageList 누적 
		uploadfileNames.stream()
					   .forEach(uploadfileName->{
						   			emp.addImageString(uploadfileName);
					   			}
					   );	
	
		return emp;
	}

}
