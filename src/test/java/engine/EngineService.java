package engine;

import cn.hutool.core.convert.Convert;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EngineService {
    private Date startTime;
    private Kernel kernel;

    public EngineService(Kernel kernel) {
        this.kernel = kernel;
    }

    public EngineRunResponse run(EngineRunRequest req) throws Exception {
        startTime = new Date();
        DecisionFlow flow = null;
        try {
            flow = kernel.getDecisionFlow(req.getKey(), req.getVersion());
        } catch (Exception e) {
            return null;
        }

        PipelineContext ctx = new PipelineContext();

        // Fill feature value from request features
        Map<String, IFeature> features = new HashMap<>();
        for (String name : flow.getFeatureMap().keySet()) {
            IFeature feature = flow.getFeatureMap().get(name);
            if (req.getFeatures().containsKey(name)) {
                Object val = req.getFeatures().get(name);
                // Check data
                Object featureType;
                try {
                    featureType = TypeUtil.getType(val);
                } catch (Exception e) {
                    // log.error(String.format("type check error: %s", e.getMessage()));
                    continue;
                }
                if (!TypeUtil.matchType(Convert.toStr(featureType), Convert.toStr(feature.getType()))) {
                    // log.warn(String.format("request feature type is not match! %s", String.format("%s type is %s, required %s", name, core.getFeatureType(featureType), feature.getType())));
                    continue;
                }
                features.put(name, feature);
                if (feature.getType() == FeatureType.TypeDate) {
                    Date valDate = Convert.toDate(val.toString());
                    features.get(name).setValue(valDate);
                } else {
                    features.get(name).setValue(val);
                }
            } else {
                // log.warn(String.format("request lack feature: %s", name));
            }
        }
        // log.info(String.format("======request features %s======", features.toString()));
        ctx.setFeatures(features);
        flow.run(ctx);

        DecisionResult result = ctx.getDecisionResult();
        return this.dataAdapter(req, result);
    }

    // 数据适配
    public EngineRunResponse dataAdapter(EngineRunRequest req, DecisionResult result) {
        EngineRunResponse resp = new EngineRunResponse();
        resp.setKey(req.getKey());
        resp.setReqId(req.getReqId());
        resp.setUid(req.getUid());
        // resp.setStartTime(TimeFormat(service.getStartTime()));

        List<Map<String, Object>> features = new ArrayList<>();

//        for (Feature feature : result.getFeatures()) {
//            Object value = feature.getValue();
//            boolean ok = feature.isValueValid();
//            Map<String, Object> featureMap = new HashMap<>();
//            featureMap.put("name", feature.getName());
//            featureMap.put("value", value);
//            featureMap.put("isDefault", !ok);
//            features.add(featureMap);
//        }
//        resp.setFeatures(features);

        List<Map<String, Object>> tracks = new ArrayList<>();
        int i = 1;
        for (Track track : result.getTracks()) {
            Map<String, Object> trackMap = new HashMap<>();
            trackMap.put("index", i);
            trackMap.put("name", track.getName());
            trackMap.put("label", track.getLabel());
            tracks.add(trackMap);
            i++;
        }
        resp.setTracks(tracks);

//        List<Map<String, Object>> hitRules = new ArrayList<>();
//        for (HitRule rule : result.getHitRules()) {
//            Map<String, Object> ruleMap = new HashMap<>();
//            ruleMap.put("id", rule.getId());
//            ruleMap.put("name", rule.getName());
//            ruleMap.put("label", rule.getLabel());
//            hitRules.add(ruleMap);
//        }
//        resp.setHitRules(hitRules);

        List<Map<String, Object>> nodeResults = new ArrayList<>();
//        for (NodeResult nodeResult : result.getNodeResults()) {
//            if (nodeResult == null) {
//                continue;
//            }
//            Map<String, Object> nodeResultMap = new HashMap<>();
//            nodeResultMap.put("name", nodeResult.getName());
//            nodeResultMap.put("id", nodeResult.getId());
//            nodeResultMap.put("Kind", nodeResult.getKind().toString());
//            nodeResultMap.put("tag", nodeResult.getTag());
//            nodeResultMap.put("label", nodeResult.getLabel());
//            nodeResultMap.put("IsBlock", nodeResult.isBlock());
//            nodeResultMap.put("Value", nodeResult.getValue());
//            nodeResultMap.put("Score", nodeResult.getScore());
//            nodeResults.add(nodeResultMap);
//        }
        resp.setNodeResults(nodeResults);

//        resp.setRunTime(TimeSince(service.getStartTime()));
//        resp.setEndTime(TimeFormat(new Date()));

        return resp;
    }
}




