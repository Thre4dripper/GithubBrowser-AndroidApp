# Github Browser
This is simple **Github Browsing Android App**. That can display all the Branches and Issues of a Github Repo provided that it is public. It can also show all the commits with all the important details of a particular commit.

### Instruction
When you first open the app, it will ask you to insert a repo to track its details from a Add Repo Screen. This screen will also verify whether that repo is valid or not and show all the errors related to the input.

> **Valid Input Example:-** 
>Owner/Organization: torvalds
>Repo Name: linux
> 
>or 
>
>Owner/Organization: google
>Repo Name: retrofit


After adding the repo it will be added to a offline database and saved for later use. The repo card will also shows a **send icon**, that will send its details like Repo Name, description and link to that repo to other text based apps like **Whatsapp**, **Gmail**, etc...

Open the repo by clicking on the card, the app will show a details screen showing repo details with two tabs, one for branches and other for issues. The issues tab will also show no of issues currently active.

Tap on any **Branch** to show all the commits of that Branch with all the necessary details of the commit.

Tap on **Issues** tab to show all the active issues of the repo with all the necessary details of that issue.

### For Developers
Simply Import the project to the android studio either by zip file or directly from repo's link and wait for the gradle plugin to load all the dependencies required to compile this app.

This app is completely made in Kotlin language.

>**Note:** This App is successfully tested on **Android Studio Bumblebee 2021.1.1 Patch 1**

Feel free to contribute...
