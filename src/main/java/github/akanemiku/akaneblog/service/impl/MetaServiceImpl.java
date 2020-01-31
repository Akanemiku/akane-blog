package github.akanemiku.akaneblog.service.impl;

import github.akanemiku.akaneblog.constant.WebConst;
import github.akanemiku.akaneblog.dto.MetaDTO;
import github.akanemiku.akaneblog.model.Meta;
import github.akanemiku.akaneblog.repository.MetaRepository;
import github.akanemiku.akaneblog.repository.dao.MetaDao;
import github.akanemiku.akaneblog.service.MetaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MetaServiceImpl implements MetaService {

    @Autowired
    private MetaDao metaDao;
    @Autowired
    private MetaRepository metaRepository;

    @Override
    public List<MetaDTO> getMetaList(String type, String orderBy, int limit) {
        if (StringUtils.isNotBlank(type)) {
            if (StringUtils.isBlank(orderBy)) {
                orderBy = "count desc, a.mid desc";
            }
            if (limit < 1 || limit > WebConst.MAX_POSTS) {
                limit = 10;
            }
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("type", type);
            paraMap.put("order", orderBy);
            paraMap.put("limit", limit);
            return metaDao.selectFromSql(paraMap);
        }
        return null;
    }

    @Override
    public Long getMetasCountByType(String type) {
        return metaRepository.findMetasCountByType(type);
    }

    @Override
    public Meta getMetaByNameAndType(String type, String name) {
        // TODO name为空异常
        return metaRepository.findMetaByNameAndType(type,name);
    }
}
