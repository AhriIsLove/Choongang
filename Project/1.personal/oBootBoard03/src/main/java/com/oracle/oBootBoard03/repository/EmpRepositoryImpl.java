package com.oracle.oBootBoard03.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.oracle.oBootBoard03.domain.Dept;
import com.oracle.oBootBoard03.domain.Emp;
import com.oracle.oBootBoard03.domain.EmpImage;
import com.oracle.oBootBoard03.dto.EmpDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmpRepositoryImpl implements EmpRepository {

	private final EntityManager em;
	@Value("${com.oracle.oBootBoard03.upload.path}")
	private String uploadPath;
	
	@Override
	public List<Emp> findAllEmp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Emp empSave(Emp emp) {
		System.out.println("EmpRepositoryImpl empSave emp->"+emp);
		em.persist(emp);
		return emp;
	}

	@Override
	public Long empTotalcount() {
		System.out.println("EmpRepositoryImpl emp/list Strart...");
		TypedQuery<Long> query = 
				em.createQuery("select count(e) from Emp e where del_status = false", Long.class); // Emp.class 대신 Long.class
		Long totalCountLong = query.getSingleResult();

		return totalCountLong;
	}

	@Override
	public List<EmpDto> findPageEmp(EmpDto empDto) {
	    String nativeSql = "SELECT e.emp_no, e.emp_id, e.emp_password, e.emp_name, e.email, e.emp_tel, e.sal, e.del_status, "
	            + "e.dept_code, e.emp_level, e.in_date, d.dept_name "
	            + "FROM ( "
	            + "    SELECT ROWNUM rn, a.* "
	            + "    FROM ( "
	            + "        SELECT e.emp_no, e.emp_id, e.emp_password, e.emp_name, e.email, e.emp_tel, e.sal, "
	            + "               e.del_status, e.dept_code, e.emp_level, e.in_date "
	            + "        FROM Emp e "
	            + "        WHERE e.del_status = 0 "
	            + "        ORDER BY e.emp_no "
	            + "    ) a "
	            + ") e "
	            + "JOIN Dept d ON e.dept_code = d.dept_code "
	            + "WHERE rn BETWEEN :start AND :end";
	    
	    System.out.println("EmpRepositoryImpl findPageEmp nativeSql kkk->"+nativeSql);

	    // 중요: Emp.class를 제거하고 그냥 네이티브 쿼리만 실행
	    Query query = em.createNativeQuery(nativeSql);

	    // 파라미터 설정
	    query.setParameter("start", empDto.getStart());
	    query.setParameter("end", empDto.getEnd());

	    // 결과를 Object[] 배열로 받기
	    List<Object[]> queryResults = query.getResultList();
	    List<EmpDto> empDtoList = new ArrayList<>();
	    
	    for (Object[] result : queryResults) {
	        EmpDto upEmpDto = new EmpDto();
	        upEmpDto.setEmp_no(((Number)result[0]).intValue());
	        upEmpDto.setEmp_id((String)result[1]);
	        upEmpDto.setEmp_password((String)result[2]);
	        upEmpDto.setEmp_name((String)result[3]);
	        upEmpDto.setEmail((String)result[4]);
	        upEmpDto.setEmp_tel((String)result[5]);
	        upEmpDto.setSal(result[6] != null ? ((Number)result[6]).longValue() : null);
	        upEmpDto.setDel_status(result[7] != null ? ((Number)result[7]).intValue() == 1 : false);
	        upEmpDto.setDept_code(((Number)result[8]).intValue());
	        upEmpDto.setEmp_level(((Number)result[9]).intValue());
	        // in_date는 LocalDate로 변환 필요
	        if (result[10] != null) {
	            if (result[10] instanceof java.sql.Timestamp) {
		            upEmpDto.setIn_date(((java.sql.Timestamp)result[10]).toLocalDateTime());
	            }
	        }
	        upEmpDto.setDept_name((String)result[11]);
	        
	        // 이미지 정보 가져오기 - 각 직원에 대한 이미지를 별도 쿼리로 조회
	        int empNo = upEmpDto.getEmp_no();
	        String imageQuery = "SELECT filename FROM emp_image_list WHERE emp_emp_no = :empNo and order_num = 0";
	        Query imgQuery = em.createNativeQuery(imageQuery);
	        imgQuery.setParameter("empNo", empNo);

	        try {
	            // 결과가 있는 경우
	            List<?> results = imgQuery.getResultList();
	            if (!results.isEmpty()) {
	                String imageResult = (String) results.get(0);
	                // String imageWebPath = imageResult.replace("\\", "/");
	                upEmpDto.setSimage("s_"+imageResult);
	             } else {
	                // 이미지가 없는 경우 기본값 설정
	                upEmpDto.setSimage(null); // 또는 기본 이미지 경로
	            }
	        } catch (Exception e) {
	            // 예외 처리
	            System.out.println("이미지 조회 중 오류 발생: " + e.getMessage());
	            upEmpDto.setSimage(null); // 또는 기본 이미지 경로
	        }
	        
	        empDtoList.add(upEmpDto);
	    }
	    
	    System.out.println("EmpRepositoryImpl findPageEmp empDtoList->"+empDtoList);
	    
	    return empDtoList;    
	}


	private List<EmpDto> findPageEmp3(EmpDto empDto) {

	    String nativeSql = "SELECT e.emp_no, e.emp_id, e.emp_password, e.emp_name, e.email, e.emp_tel, e.sal, e.del_status, "
	            + "e.dept_code, e.emp_level, e.in_date, d.dept_name "
	            + "FROM ( "
	            + "    SELECT ROWNUM rn, a.* "
	            + "    FROM ( "
	            + "        SELECT e.emp_no, e.emp_id, e.emp_password, e.emp_name, e.email, e.emp_tel, e.sal, "
	            + "               e.del_status, e.dept_code, e.emp_level, e.in_date "
	            + "        FROM Emp e "
	            + "        WHERE e.del_status = 0 "
	            + "        ORDER BY e.emp_no "
	            + "    ) a "
	            + ") e "
	            + "JOIN Dept d ON e.dept_code = d.dept_code "
	            + "WHERE rn BETWEEN :start AND :end";
	    
	    System.out.println("EmpRepositoryImpl findPageEmp nativeSql kkk->"+nativeSql);

	    // 중요: Emp.class를 제거하고 그냥 네이티브 쿼리만 실행
	    Query query = em.createNativeQuery(nativeSql);

	    // 파라미터 설정
	    query.setParameter("start", empDto.getStart());
	    query.setParameter("end", empDto.getEnd());

	    // 결과를 Object[] 배열로 받기
	    List<Object[]> queryResults = query.getResultList();
	    List<EmpDto> empDtoList = new ArrayList<>();
	    
	    for (Object[] result : queryResults) {
	        EmpDto upEmpDto = new EmpDto();
	        upEmpDto.setEmp_no(((Number)result[0]).intValue());
	        upEmpDto.setEmp_id((String)result[1]);
	        upEmpDto.setEmp_password((String)result[2]);
	        upEmpDto.setEmp_name((String)result[3]);
	        upEmpDto.setEmail((String)result[4]);
	        upEmpDto.setEmp_tel((String)result[5]);
	        upEmpDto.setSal(result[6] != null ? ((Number)result[6]).longValue() : null);
	        upEmpDto.setDel_status(result[7] != null ? ((Number)result[7]).intValue() == 1 : false);
	        upEmpDto.setDept_code(((Number)result[8]).intValue());
	        upEmpDto.setEmp_level(((Number)result[9]).intValue());
	        // in_date는 LocalDate로 변환 필요
	        if (result[10] != null) {
	            if (result[10] instanceof java.sql.Date) {
	                // java.sql.Date를 LocalDateTime으로 변환
	                java.sql.Date sqlDate = (java.sql.Date)result[10];
	                upEmpDto.setIn_date(sqlDate.toLocalDate().atStartOfDay());
	            } else if (result[10] instanceof java.sql.Timestamp) {
	                // java.sql.Timestamp를 LocalDateTime으로 변환
	                upEmpDto.setIn_date(((java.sql.Timestamp)result[10]).toLocalDateTime());
	            }
	        }
	        
	        upEmpDto.setDept_name((String)result[11]);
	        empDtoList.add(upEmpDto);
	    }
	    
	    System.out.println("EmpRepositoryImpl findPageEmp empDtoList->"+empDtoList);
	    
	    return empDtoList;    
	}

	@Override
	public EmpDto findById(int emp_no) {
		Emp  emp  = em.find(Emp.class, emp_no);
		// 부서명 Setting
		Dept dept = em.find(Dept.class, emp.getDept_code());
		emp.changeDept_name(dept.getDept_name());
		
		// Image Setting
        // 이미지 정보 가져오기 - 각 직원에 대한 이미지를 별도 쿼리로 조회
		// 이미지 목록 조회
		List<EmpImage> empImages = new ArrayList<>();
        String imageQuery = "SELECT filename FROM emp_image_list WHERE emp_emp_no = :emp_no order by order_num ";
        // 네이티브 쿼리 실행
		Query nativeQuery = em.createNativeQuery(imageQuery);
		nativeQuery.setParameter("emp_no", emp_no);
		List<String> filenameList = nativeQuery.getResultList();
		
	    EmpDto empDto = entityToDTO(emp, filenameList);

		return empDto;
	}
	
	private EmpDto entityToDTO(Emp emp, List<String> filenameList) {
		EmpDto empDTO = EmpDto.builder()
	                          .emp_no(emp.getEmp_no())
	                          .dept_code(emp.getDept_code())
	                          .dept_name(emp.getDept_name())
	                          .email(emp.getEmail())
	                          .emp_id(emp.getEmp_id())
	                          .emp_level(emp.getEmp_level())
	                          .emp_name(emp.getEmp_name())
	                          .emp_tel(emp.getEmp_tel())
	                          .sal(emp.getSal())
	                          .emp_tel(emp.getEmp_tel())
	                          .build()
	                           ;
		
		System.out.println("Service EmpDto get entityToDTO imageList Before" );
		
	    if(filenameList == null || filenameList.size() == 0 ){
		      return empDTO;
		}
		
	    empDTO.setUploadFileNames(filenameList);
		System.out.println("Service EmpDto get entityToDTO imageList After" );

		return empDTO;
	}


}
