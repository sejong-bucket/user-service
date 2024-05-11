package com.sejong.bucketmanager.domain.locker.service;


import com.sejong.bucketmanager.domain.locker.entity.Locker;
import com.sejong.bucketmanager.domain.locker.entity.detail.LockerDetail;
import com.sejong.bucketmanager.domain.locker.entity.detail.LockerDetailStatus;
import com.sejong.bucketmanager.domain.locker.repository.LockerDetailJpaRepository;
import com.sejong.bucketmanager.domain.locker.service.dto.CreateLockerDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.sejong.bucketmanager.domain.locker.controller.request.NumberIncreaseDirection.DOWN;
import static com.sejong.bucketmanager.domain.locker.entity.detail.LockerDetailStatus.NON_RESERVED;

@Service
@RequiredArgsConstructor
public class LockerDetailService{
    private final LockerDetailJpaRepository lockerDetailJpaRepository;

    public void createLockerDetails(CreateLockerDetailDto createLockerDetailDto, Locker saveLocker) {
        int totalColumns = createLockerDetailDto.getTotalColumn();
        int totalRows = createLockerDetailDto.getTotalRow();
        List<LockerDetail> lockerDetails = null;
        if (createLockerDetailDto.getNumberIncreaseDirection().equals(DOWN)) {
            lockerDetails = makeLockerDetails(saveLocker, totalColumns, totalRows);
        } else {
            lockerDetails = makeLockerDetails(saveLocker, totalRows, totalColumns);
        }
        lockerDetailJpaRepository.saveAll(lockerDetails);
    }

    private List<LockerDetail> makeLockerDetails(Locker saveLocker, int master, int slave) {
        List<LockerDetail> saveLockerDetails = new ArrayList<>();
        int num = 0;
        for (int i = 1; i <= master; i++) {
            for (int j = 1; j <= slave; j++) {
                saveLockerDetails.add(getLockerDetail(saveLocker, ++num, i, j));
            }
        }
        return saveLockerDetails;
    }

    private LockerDetail getLockerDetail(Locker saveLocker, int num, int i, int j) {
        return LockerDetail.builder()
                .locker(saveLocker)
                .lockerNum(Integer.toString(num))
                .rowNum(Integer.toString(j))
                .columnNum(Integer.toString(i))
                .build();
    }
}
