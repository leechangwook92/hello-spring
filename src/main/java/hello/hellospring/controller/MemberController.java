package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Component로 올려줌
// 어노테이션 생성시 컨테이너에 올라감
@Controller
public class MemberController {

    private MemberService memberService;

    // 가져다 쓸 클레스를 선언후 생성자를 써주면 컨테이너에 등록이 되면서 이 생성자를 호출해서
    // 동일하게 가져다쓸 클레스에도 컨테이너에 올려주는 작업이 필요함.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        member.setTitle(form.getTitle());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String selectAllMember(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);

        return "members/memberList";
    }

    @PostMapping("/members/delete/{id}")
    public String deleteMember(@PathVariable("id") Long id) {
        System.out.println("id = " + id);
        memberService.deleteOne(id);

        return "redirect:/";
    }
    //REquestParam Pathvariable requestbody

    @PostMapping("/members/update/{id}/{title}")
    public String update(@PathVariable("id") Long id, @PathVariable("title") String title,Member member){
        System.out.println("id = " + id);
        System.out.println("title = " + title);
        memberService.updateMember(id, title, member);
        return "redirect:/members";
    }

}
