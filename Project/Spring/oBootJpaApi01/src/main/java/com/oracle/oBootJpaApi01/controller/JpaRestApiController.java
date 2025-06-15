package com.oracle.oBootJpaApi01.controller;

import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootJpaApi01.domain.Member;
import com.oracle.oBootJpaApi01.service.MemberService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




//RestAPI용 Controller 설정
@RestController
//-@Controller
//-@ResponseBody
//LOGGER 설정
@Slf4j
@RequiredArgsConstructor
public class JpaRestApiController {
	private final MemberService memberService;
	
	@RequestMapping("/helloText")
	public String helloText() {
		System.out.println("JpaRestApiController helloText Start...");
		String hello = "안녕";
//		@Slf4j : LOGGER
		log.info("log.info : {}.", hello);
		
		return hello;
	}
	
	// 모든 데이터 전송
	@GetMapping("/restApi/v1/members")
	public List<Member> membersVer1(){
		System.out.println("JpaRestApiController /restApi/v1/members Start...");
		List<Member> listMember = memberService.getListAllMember();
		System.out.println("JpaRestApiController /restApi/v1/members listMember.size() : " + listMember.size());
		
		return listMember;
	}
	
	// 일부 데이터 및 가공 데이터 전송
	// 일부 데이터를 가지는 반환용 Dto 클래스 생성 후 활용
	@GetMapping("/restApi/v21/members")
	public Result membersVer21(){
		List<Member> findMembers = memberService.getListAllMember();
		System.out.println("JpaRestApiController /restApi/v21/members listMember.size() : " + findMembers.size());
		List<MemberRtnDto> resultList = new ArrayList<MemberRtnDto>();
		
		for(Member member : findMembers) {
			MemberRtnDto memberRtnDto = new MemberRtnDto(member.getName(), member.getSal());
			System.out.println("JpaRestApiController /restApi/v21/members getName : " + memberRtnDto.getName());
			System.out.println("JpaRestApiController /restApi/v21/members getSal : " + memberRtnDto.getSal());
			resultList.add(memberRtnDto);
		}
		System.out.println("JpaRestApiController /restApi/v21/members resultList.size() : " + resultList.size());
		
		return new Result(resultList.size(), resultList);
	}

	// 일부 데이터 및 가공 데이터 전송
	// Lambda(람다) 방식
	@GetMapping("/restApi/v22/members")
	public Result membersVer22(){
		List<Member> findMembers = memberService.getListAllMember();
		System.out.println("JpaRestApiController /restApi/v22/members listMember.size() : " + findMembers.size());
		List<MemberRtnDto> memberCollect = 
				findMembers.stream()
				.map(member->new MemberRtnDto(member.getName(), member.getSal()))
				.collect(Collectors.toList());
		System.out.println("JpaRestApiController /restApi/v22/members memberCollect.size() : " + memberCollect.size());
		
		return new Result(memberCollect.size(), memberCollect);
	}
	
	@Data
	@AllArgsConstructor
	private class MemberRtnDto{
		private String name;
		private Long sal;
	}

	@Data
	@RequiredArgsConstructor
	private class Result<T>{
		private final int totCount;
//		private final Long totSal;
		private final T data;
	}
	
	// postman ---> Body ---> raw ---> JSON	 
    // - 예시{"name" : "kkk222", "sal":"4000"}
	// @RequestBody : Json(member)으로 온것을 --> Member member Setting
	@PostMapping("/restApi/v1/memberSave")
	public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) { // @Valid : 값이 유효한지 확인
		System.out.println("JpaRestApiController /restApi/v1/memberSave member : " + member);
		log.info("member.getName() : {}", member.getName());
		log.info("member.getSal() : {}", member.getSal());
		
		Long id = memberService.saveMember(member);
		return new CreateMemberResponse(id);
	}
	
	// 목적 : Entity Member member --> 직접 화면이나 API위한 Setting 금지
	// 예시 : @NotEmpty --> @Column(name = "userName")
	@PostMapping("/restApi/v2/memberSave")
	public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest cMember) {
		System.out.println("JpaRestApiController /restApi/v2/memberSave cMember : " + cMember);
		log.info("cMember.getName() : {}", cMember.getName());
		log.info("cMember.getSal() : {}", cMember.getSal());
		
		//외부에서 Member에 지정된 값 외의 값이 설정된 Member 저장을 막기위해
		Member member= new Member();
		member.setName(cMember.getName());
		member.setSal(cMember.getSal());
		
		Long id = memberService.saveMember(member);
		return new CreateMemberResponse(id);
	}
	
	@Data
//	@RequiredArgsConstructor //@Data에 포함되어 있음
	private class CreateMemberResponse{
		private final Long id;
	}
	
	@Data
	static private class CreateMemberRequest{
		@NotEmpty
		private String name;
		private Long sal;
	}
	
	/*
	 *   단일 Id 조회 API
	 *   URI 상에서 '{ }' 로 감싸여있는 부분과 동일한 변수명을 사용하는 방법
	 *   해당 데이터가 있으면 업데이트를 하기에 
	 *   Get요청이 여러번 실행되어도 해당 데이터는 같은 상태이기에 멱등
	 *   - 서버롭부터 가져온 데이터의 변동이 없으면 뷰에서 새로고침 하지 않음 
	 */
	@GetMapping("/restApi/v15/members/{id}")
	// @PathVariable : URI의 파라미터 값을 받는다
	public Member membersVer15(@PathVariable("id") Long id) {
		System.out.println("JpaRestApiController /restApi/v15/members/{id} : " + id);
		Member findMember = memberService.findByMember(id);
		System.out.println("JpaRestApiController /restApi/v15/members findMember : " + findMember);
		
		return findMember;
	}
	
	@PutMapping("/restApi/v21/members/{id}")
	public UpdateMemberResponse updateMemberV21(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberRequest uMember) {
		System.out.println("JpaRestApiController updateMemberV21 id : " + id);
		System.out.println("JpaRestApiController updateMemberV21 uMember : " + uMember);
		int result = memberService.updateMember(id, uMember.getName(), uMember.getSal());
		Member findMember = memberService.findByMember(id);
//		
		return new UpdateMemberResponse(findMember.getId(), findMember.getName(), findMember.getSal());
	}

	// static 안해도 되지만 하는것을 권장...?
	@Data
	static class UpdateMemberRequest{
		private String name;
		private Long sal;
	}
	
	@Data
	@AllArgsConstructor
	class UpdateMemberResponse{
		private Long id;
		private String name;
		private Long sal;
	}
	
	@DeleteMapping("/restApi/v21/deleteMember/{id}")
	public CreateMemberResponse deleteMemberV21(@PathVariable("id")Long id) {
		System.out.println("JpaRestApiController deleteMemberV21 id : " + id);
		int result = memberService.deleteMember(id);
		
		return new CreateMemberResponse(id);
	}
}
