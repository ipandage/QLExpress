package engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PipelineContext {

    private Map<String, Rule> hitRules;
    private ReentrantReadWriteLock hMutex;

    private List<Track> tracks;
    private ReentrantReadWriteLock tMutex;

    private Map<String, NodeResult> nodeResults;
    private ReentrantReadWriteLock nMutex;

    private Map<String, IFeature> features; // stores all assigned features in the context
    private ReentrantReadWriteLock fMutex;

    public PipelineContext() {
        this.hitRules = new HashMap<>();
        this.hMutex = new ReentrantReadWriteLock();
        this.tracks = new ArrayList<>();
        this.tMutex = new ReentrantReadWriteLock();
        this.nodeResults = new HashMap<>();
        this.nMutex = new ReentrantReadWriteLock();
        this.features = new HashMap<>();
        this.fMutex = new ReentrantReadWriteLock();
    }

    public void setHitRules(Map<String, Rule> hitRules) {
        if (hitRules.isEmpty()) {
            return;
        }
        hMutex.writeLock().lock();
        try {
            this.hitRules.putAll(hitRules);
        } finally {
            hMutex.writeLock().unlock();
        }
    }

    public Map<String, Rule> getHitRules() {
        hMutex.readLock().lock();
        try {
            return Collections.unmodifiableMap(hitRules);
        } finally {
            hMutex.readLock().unlock();
        }
    }

    public void addTrack(INode node) {
        Track track = new Track( node.getInfo().getId(), node.getName(), node.getInfo().getLabel(),
            node.getInfo().getTag(), node.getType());

        tMutex.writeLock().lock();
        try {
            tracks.add(track);
        } finally {
            tMutex.writeLock().unlock();
        }
    }

    public List<Track> getTracks() {
        tMutex.readLock().lock();
        try {
            return Collections.unmodifiableList(tracks);
        } finally {
            tMutex.readLock().unlock();
        }
    }

    public void setNodeResult(String nodeId, NodeResult result) {
        nMutex.writeLock().lock();
        try {
            nodeResults.put(nodeId, result);
        } finally {
            nMutex.writeLock().unlock();
        }
    }

    public NodeResult getNodeResult(String nodeId) {
        nMutex.readLock().lock();
        try {
            return nodeResults.get(nodeId);
        } finally {
            nMutex.readLock().unlock();
        }
    }

    public void setFeatures(Map<String, IFeature> features) {
        if (features.isEmpty()) {
            return;
        }
        fMutex.writeLock().lock();
        try {
            this.features.putAll(features);
        } finally {
            fMutex.writeLock().unlock();
        }
    }

    public Map<String, IFeature> getFeatures(List<String> depends) {
        if (depends.size() == 0) {
            return null;
        }

        //from local
        Map<String, IFeature> result = new HashMap<String, IFeature>();
        List<String> remoteList = new ArrayList<String>();

        fMutex.readLock().lock();
        Map<String, IFeature> localFeatures = features;
        fMutex.readLock().unlock();

        for (String name : depends) {
            if (localFeatures.containsKey(name)) {
                result.put(name, localFeatures.get(name));
            } else {
                remoteList.add(name);
            }
        }

        //from remote
        if (remoteList.size() == 0) {
            //远程调用后new的时候要知道类型
            return null;
        }

        //curl
        return null;
    }


    public void addFeature(String key, IFeature feature) {
        fMutex.writeLock().lock();
        try {
            features.put(key, feature);
        } finally {
            fMutex.writeLock().unlock();
        }
    }

    public IFeature getFeature(String key) {
        fMutex.readLock().lock();
        try {
            return features.get(key);
        } finally {
            fMutex.readLock().unlock();
        }
    }

    // 添加命中规则
    public void addHitRule(Rule rule) {
        fMutex.writeLock().lock();
        try {
            hitRules.put(rule.getName(), rule);
        } finally {
            tMutex.writeLock().unlock();
        }
    }

    public void addNodeResult(String name, NodeResult nodeResult) {
        synchronized (nMutex) {
            nodeResults.put(name, nodeResult);
        }
    }

    public Map<String, IFeature> getAllFeatures() {
        fMutex.readLock().lock();
        try {
            return this.features;
        } finally {
            fMutex.readLock().unlock();
        }
    }

    public Map<String, NodeResult> getNodeResults() {
        fMutex.readLock().lock();
        try {
            return this.nodeResults;
        } finally {
            fMutex.readLock().unlock();
        }
    }

    public DecisionResult getDecisionResult() {
        return new DecisionResult(
            getHitRules(),
            getTracks(),
            getAllFeatures(),
            getNodeResults()
        );
    }



}
