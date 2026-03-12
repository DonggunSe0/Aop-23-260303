package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class WiseSayingRepository {
    private List<WiseSaying> wiseSayingList = new ArrayList<>(){{
        add(new WiseSaying(1, "명언1", "작가1"));
        add(new WiseSaying(2, "명언2", "작가2"));
        add(new WiseSaying(3, "명언3", "작가3"));
        add(new WiseSaying(4, "명언4", "작가4"));
        add(new WiseSaying(5, "명언5", "작가5"));
    }}; // array list에 초기값 넣는 방법, 초기값이 5개 들어가 있음

    private int lastId = 0;

    public WiseSaying save(WiseSaying wiseSaying) {
        if (wiseSaying.isNew()) {
            wiseSaying.setId(++lastId);
        }
        wiseSayingList.add(wiseSaying);
        return wiseSaying;
    }

    public List<WiseSaying> findAll() {
        return wiseSayingList;
    }

    public Optional<WiseSaying> findById(int id) {
        Optional<WiseSaying> wiseSaying = wiseSayingList.stream()
                .filter(w->w.getId() == id)
                .findFirst();

        return wiseSaying;
    }

    public void delete(WiseSaying wiseSaying) {
        wiseSayingList.remove(wiseSaying);
    }
}
