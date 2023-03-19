package engine;

public class FeatureFactory {

    public static IFeature newFeature(String name, FeatureType kind) {
        switch (kind) {
            case TypeInt:
            case TypeFloat:
                return new TypeNumFeature(name, kind);
//            case TypeString:
//                return new TypeStringFeature(name, kind);
//            case TypeBool:
//                return new TypeBoolFeature(name, kind);
//            case TypeDate:
//                return new TypeDateFeature(name, kind);
//            case TypeArray:
//                return new TypeArrayFeature(name, kind);
//            case TypeMap:
//                return new TypeMapFeature(name, kind);
            default:
                return new TypeDefaultFeature(name, kind);
        }
    }
}
