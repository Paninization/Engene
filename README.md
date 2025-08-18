# Engene
Library using ashley to make your life easier in libgdx.


## How to use: 
For now there is no official release, so you can use jitpack for take the last build from
the last commit, then you can add to your libgdx project. 
for example: 
```groovy
dependencies{
    //other stuff of libgdx 
    implementation 'com.github.Paninization:Engene:a7a3223260'
    implementation "com.badlogicgames.ashley:ashley:1.7.3"
}
```

The you can start with basic code, so first at all create a new Engene instance and add basics (but not mandatory) Systems:
```java
    Engene engene = new Engene();
    engene.addSystem(new RenderSystem(batch,camera, Priority.RENDER)); //You need a batch and a camera made by you 
    engene.addSystem(new TransformSystem(Priority.UPDATE_TRASFORM));
```
Then you can build and add entities: 
```java
    Entity e = new Entity();
    TransformComponent transform = new TransformComponent();
    e.add(transform);
```

for more info check the documentation (that for now don't exist lmao)

## Disclaimer 
This library is in development status, so is really unstable, use at your own risk. 


