# reddit

```
Assumptions:
* Code is written with expansion in mind, meaning eventual dependency on an API and implementation of 
  more features.
* App will support many different languages, which is the reason for using strings.xml.
```
```
Intentional design decisions:
* The use of singletons with PostManager. While it is true that misuse and overdependency on singletons are bad, 
  if used correctly they are a valuable way to implement a global source of data. This way, with the ArrayList 
  used in this Manager, you can consistently operate on the same ArrayList when up/downvoting, adding posts, etc.
* The use of a custom Application class. This is for access to Context when Managers need it as well as 
  initialization in the future  for libraries like Facebook, Firebase, or Branch.
* A GenericActivity class that other activities derive from for navigation between different activities 
  and modularity.
* DTOs even though there is no JSON at the moment, most data is now transferred between JSON objects, so with the 
  idea that the app  will eventually be dependent on an API, using a DTO seemed like the best choice.
* Using a RecyclerView because:
⋅⋅* Efficiency when the list needs to be dynamic (changing number of votes, order of the list). 
⋅⋅* LayoutManager if the user wants to sort things into a grid for future cases.
⋅⋅* Enforced ViewHolder pattern for coding convention.
* ConstraintLayout for all my layouts to improve performance from measurement, layout, and drawing traversal of 
  layout files. Android has view trees that they traverse whenever a layout needs to be drawn, so when you 
  have a flat structure (ConstraintLayout) with very little to no nesting, your performance improves. 
  More info here: https://android-developers.googleblog.com/2017/08/understanding-performance-benefits-of.html.
* Use of @strings, so when another language needs to be added, you can just use a different strings.xml file. 
  I also thought about RTL and LTR support, but it requires a lot more programmatic approach rather than just 
  the support option in the layout file.
```
```
Additionally, unfortunately my harddrive doesn't have enough space to support the emulators because of another 
project I'm doing so I wrote code that worked on both large and small screens. If I had more space to test, 
I would have included layouts for tablets, other standard Android devices, as well as special 
devices (the Essential phone).
```
```
App was tested by these guidelines: https://developer.android.com/docs/quality-guidelines/
```
