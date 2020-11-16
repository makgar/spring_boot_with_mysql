package lv.learning.spring_boot_with_mysql.controller;

import lv.learning.spring_boot_with_mysql.model.entity.TitleEntity;
import lv.learning.spring_boot_with_mysql.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/titles", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class TitleController {

    @Autowired
    private TitleService titleService;

    @GetMapping
    public Page<TitleEntity> readTitles(
            // Does not work atm
            @SortDefault(sort = "titlePk.title", direction = Sort.Direction.ASC)
            Sort sort,
            Pageable pageRequest
    ) {
        return titleService.readTitles(sort, pageRequest);
    }
}
