# CummulativeMax
Calculates the running maximum. Coded during TS15 in an attempt to speed up part of the workflow for workflow deconstruction.
However there are a few issues:
* The plugin is not much faster.
* It would need to be improved to run across any available dimension. 
* Maybe I should have made an [ops](https://imagej.net/ImageJ_Ops) instead of a plugin.

[Part of the surfcut macro that I aimed to speed up](https://github.com/sverger/SurfCut/blob/master/SurfCut.ijm#L76):
```
//Edge detect
getDimensions(w, h, channels, slices, frames);
print (slices);
for (img=0; img<slices; img++){
  print("Edge detect projection" + img + "/" + slices);
  slice = img+1;
  selectWindow(imgName);
  run("Z Project...", "stop=&slice projection=[Max Intensity]");
}
print("Concatenate images");
run("Images to Stack", "name=Stack-0 title=[]");
 ```
