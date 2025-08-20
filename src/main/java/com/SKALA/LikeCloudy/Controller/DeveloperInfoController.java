package com.SKALA.LikeCloudy.Controller;

import com.SKALA.LikeCloudy.Config.DeveloperProperties;
import com.SKALA.LikeCloudy.DTO.DeveloperInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DeveloperInfoController {

    private final DeveloperProperties props;

    public DeveloperInfoController(DeveloperProperties props) {
        this.props = props;
    }

    @GetMapping("/developer-info")
    public DeveloperInfo info() {
        var owner = props.getOwner();
        var team = props.getTeam();

        return new DeveloperInfo(
                new DeveloperInfo.Owner(owner.getName(), owner.getRole(), owner.getLevel()),
                new DeveloperInfo.Team(team.getPosition(), team.getDetail()));
    }
}