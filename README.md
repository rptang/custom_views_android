## custom_views_android
Android native control widget can not meet the everything during developing project always,sometimes we also need to define some views to achieve our functions ,and believe me ,this field  is full of magic and challenges ,and which is worthed to spend time to breakthrough for us who as a develpoer .This project ,as indicated in the title ,I will push the custom view which I made to the github for record and share for me and you .

---
### Switch Button
#### function implementation process:

---
### Wheel Picker
#### function implementation process:
Before we write the custom view ,you should know about the some method of "View" ,like as onMeasure(),onDraw(),onTouchEvent() which be used one by one under what condition. And then you need to think out a effective way to paint the view you want . Example for this, we want to show a list of text ,and also users can slide the list left or right and one of the list would be selected when the slide stop, of course up and down and other funciton if you want ,you just change something easily if you understand this code . Ok, we just define a "TextItem" which contains coordinate ,content ,index ,and some other attributes .Once UI changing the each of "TextItem" follows the change and execute onDraw() to paint ,which is the effective way I thought .


---
#### Rendering show
###### switch_button:
![image](https://github.com/rptang/custom_views_android/blob/master/switch_on.png)
![image](https://github.com/rptang/custom_views_android/blob/master/switch_off.png)
###### wheel_picker:
![image](https://github.com/rptang/custom_views_android/blob/master/number_pick.png)

License
-------

    Copyright 2017 Stiven rptang

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
