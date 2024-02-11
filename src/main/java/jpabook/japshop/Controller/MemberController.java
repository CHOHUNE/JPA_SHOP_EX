package jpabook.japshop.Controller;


import jakarta.validation.Valid;
import jpabook.japshop.Service.MemberService;
import jpabook.japshop.domian.Address;
import jpabook.japshop.domian.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping("members/new")
    public String createForm(Model model) {
        // Controller -> view 로 넘어갈 때 Model ( attribute로 넣음 )

        model.addAttribute("memberForm", new MemberForm());

        return "members/createMemberForm";

    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {

        //Vaild 를 추가 하면 MemberFrom 내의 @NotEmpty 어노테이션과 연계하여
        // 입력값을 필수로 입력 해줘야 한다.

        //BindingResult -> 해당 오류 내용을 담아서 화면에 출력함. 리액트와 비슷한 기능
        // 이번 예제는 createMemberForm의 input 부분에 hasError 시 name 부분에 출력되고 색상이 변경되도록 별도의 세팅이
//      // 들어간 것을 확인할 수 있다.

        // 자세한 내용은 타임리프 DOCS 에 오류메세지 검증 등 자세히 예제가 나와있다.

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);

        return "redirect:/"; //홈에 보내기
    }
}
