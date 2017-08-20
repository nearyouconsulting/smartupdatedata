import de.hybris.platform.core.PK;
Long examplePK = ...;
object = modelService.get(new PK(examplePK));
print object.getPk();
print ", ";
println object.getName();
