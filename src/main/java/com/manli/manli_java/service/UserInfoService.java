package com.manli.manli_java.service;

import com.manli.manli_java.model_auto.*;
import com.manli.manli_java.model_impl.UserInfoImpl1;
import com.manli.manli_java.model_impl.UserRecordInfoImpl1;
import com.manli.manli_java.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserInfoService {
    @Autowired
    UserInfoRepository           userInfoRepository;
    @Autowired
    TakingMedicineTimeRepository takingMedicineTimeRepository;
    @Autowired
    UserMedalRepository          userMedalRepository;
    @Autowired
    UserRecordInfoRepository     userRecordInfoRepository;
    @Autowired
    UserTkiRecordInfoRepository  userTkiRecordInfoRepository;

    public Map<String, Object> getUserTakingMedicineTime(int userId) {
        UserInfoEntity userInfoEntity = userInfoRepository.findOneByUserId(userId);
        if (null != userInfoEntity) {
            Integer takingMedicineTimeType = userInfoEntity.getTakingMedicineTime();
            TakingMedicineTimeEntity takingMedicineTimeEntity = takingMedicineTimeRepository.findOneByType(takingMedicineTimeType.shortValue());
            if (null != takingMedicineTimeEntity) {
                Map<String, Object> map = new HashMap<>();
                map.put("takingMedicineTimeType", takingMedicineTimeType);
                map.put("takingMedicineTime", takingMedicineTimeEntity.getDescription());
                return map;
            }
        }
        return null;
    }

    public UserInfoEntity setUserTakingMedicineTime(Integer userId, Integer takingMedicineTime) {
        UserInfoEntity userInfoEntity = userInfoRepository.findOneByUserId(userId);
        if (null != userInfoEntity) {
            userInfoEntity.setTakingMedicineTime(takingMedicineTime);
        }
        return null;
    }

    public void add922Medal(int userId) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        UserMedalEntity userMedalEntity = new UserMedalEntity();
        userMedalEntity.setUserId(userId);
        userMedalEntity.setType(Byte.valueOf("1"));
        userMedalEntity.setAwardTime(ts);
        userMedalEntity.setTitle("922勋章");
        userMedalEntity.setDetail("922勋章在9.22～10.22之间注册的用户获得的一种勋章");
        userMedalEntity.setStatus(Byte.valueOf("0"));

        userMedalRepository.save(userMedalEntity);

        return;
    }

    public void updateUserBaseInfo_OldVersion(int userId, Map<String, Object> baseInfo) {
        UserInfoEntity userInfoEntity = userInfoRepository.findOneByUserId(userId);
        if (null != userInfoEntity) {
            for (Map.Entry<String, Object> entry : baseInfo.entrySet()) {

                if (entry.getKey().equals("phone")) {
                    userInfoEntity.setPhone(baseInfo.get("phone").toString());
                }
            }
        } else {
            userInfoEntity = new UserInfoEntity();
            userInfoEntity.setPhone(baseInfo.get("phone").toString());
        }

        userInfoRepository.save(userInfoEntity);
    }

    public boolean isAllRequiredUserinfoSet(int userId) {
        UserInfoEntity userInfoEntity = userInfoRepository.findOneByUserIdAndStatus(userId, Byte.valueOf("0"));
        if (null == userInfoEntity ||
                null == userInfoEntity.getPhone() ||
                0 == userInfoEntity.getSex() ||
                null == userInfoEntity.getName() ||
                null == userInfoEntity.getBirthDay()
        ) {
            return false;
        }

        UserRecordInfoEntity userRecordInfoEntity = userRecordInfoRepository.findOneByUserIdAndStatus(userId, Byte.valueOf("0"));
        if (null == userRecordInfoEntity ||
                null == userRecordInfoEntity.getConfirmDate() ||
                null == userRecordInfoEntity.getFirstMedicineDate() ||
                null == userRecordInfoEntity.getCml() ||
                0 == userRecordInfoEntity.getCombinedChemotherapy()
        ) {
            return false;
        }

        List<UserTkiRecordInfoEntity> userTkiRecordInfoList = userTkiRecordInfoRepository.findAllByUserIdAndStatus(userId, Byte.valueOf("0"));
        if (userTkiRecordInfoList.size() == 0) {
            return false;
        }

        for (UserTkiRecordInfoEntity tkiInfo : userTkiRecordInfoList) {
            if (
                    tkiInfo.getTkiName() == null ||
                            tkiInfo.getTkiStartDate() == null ||
                            tkiInfo.getTkiDosage() == 0 ||
                            tkiInfo.getTkiFrequency() == 0
            ) {
                return false;
            }
        }

        return true;
    }

    public List<UserMedalEntity> getUserMedals(Integer userId) {
        UserInfoEntity userInfoEntity = userInfoRepository.findOneByUserId(userId);
        if (null != userInfoEntity) {
            String medalIds = userInfoEntity.getMedals();
            if (null != medalIds && medalIds.length() > 0) {
                List<UserMedalEntity> list = userMedalRepository.getAllByIdIn(medalIds.split(","));
                return list;
            }
        }

        return null;
    }


    public UserInfoEntity getUserBaseInfo(Integer userId) {
        UserInfoEntity userInfoEntity = userInfoRepository.findOneByUserIdAndStatus(userId, Byte.valueOf("0"));
        return userInfoEntity;
    }

    public void updateUserBaseInfo(Integer userId, UserInfoImpl1 userInfoImpl1) {
        UserInfoEntity userInfoEntity = userInfoRepository.findOneByUserIdAndStatus(userId, Byte.valueOf("0"));
        if (userInfoImpl1.getSex() != null) {
            userInfoEntity.setSex(userInfoImpl1.getSex());
        }

        if (userInfoImpl1.getBirthDay() != null) {
            userInfoEntity.setBirthDay(userInfoImpl1.getBirthDay());
        }

        if (userInfoImpl1.getPhone() != null) {
            userInfoEntity.setPhone(userInfoImpl1.getPhone());
        }

        if (userInfoImpl1.getName() != null) {
            userInfoEntity.setName(userInfoImpl1.getName());
        }

        userInfoRepository.save(userInfoEntity);

    }

    public Integer filledFieldCount(Integer userId) {
        Integer count = 0;
        UserInfoEntity userInfoEntity = userInfoRepository.findOneByUserIdAndStatus(userId, Byte.valueOf("0"));
        if (userInfoEntity != null) {
            if (userInfoEntity.getPhone() != null) {
                count++;
            }
            if (userInfoEntity.getSex() != null) {
                count++;
            }
            if (userInfoEntity.getName() != null) {
                count++;
            }
            if (userInfoEntity.getBirthDay() != null) {
                count++;
            }
        }

        UserRecordInfoEntity userRecordInfoEntity = userRecordInfoRepository.findOneByUserIdAndStatus(userId, Byte.valueOf("0"));
        if (userRecordInfoEntity != null) {
            if (userRecordInfoEntity.getHospital() != null) {
                count++;
            }
            if (userRecordInfoEntity.getDoctor() != null) {
                count++;
            }
            if (userRecordInfoEntity.getProfession() != null) {
                count++;
            }
            if (userRecordInfoEntity.getWeight() != null) {
                count++;
            }
            if (userRecordInfoEntity.getCompl() != null) {
                count++;
            }
        }

        List<UserTkiRecordInfoEntity> tkiList = userTkiRecordInfoRepository.findAllByUserIdAndStatus(userId, Byte.valueOf("0"));
        if (tkiList != null) {
            count += tkiList.stream().mapToInt((UserTkiRecordInfoEntity userTkiRecordInfoEntity) -> {
                Integer cc = 0;
                if (userTkiRecordInfoEntity.getTkiName() != null) {
                    cc++;
                }
                if (userTkiRecordInfoEntity.getTkiStartDate() != null) {
                    cc++;
                }
                if (userTkiRecordInfoEntity.getTkiDosage() != null) {
                    cc++;
                }
                if (userTkiRecordInfoEntity.getTkiFrequency() != null) {
                    cc++;
                }

                return cc;
            }).sum();
        }

        return count;
    }

    public Integer totalFieldCount() {
        Integer tableUserInfo_fieldCount = 4;
        Integer tableUserRecordInfo_fieldCount = 5; //以前是18，现在少了13项
        Integer tableUserTkiRecordInfo_fieldCount = 4;

        return (
                tableUserInfo_fieldCount +
                        tableUserRecordInfo_fieldCount +
                        tableUserTkiRecordInfo_fieldCount
        );
    }


}
