<p align="center">
    <p align="center">
   EvolveFit A gym tracking app built with Kotlin Multiplatform for Android and iOS. Track workouts , monitor progress , and achieve your fitness goals!
  </p>
  <br>
</p>

## Architecture
This project is based on a Kotlin Multiplatform (KMP) architecture with shared business logic between Android and iOS.

## Technologies
> [!TIP]
> - KMP
> - cmp
> - Room Database
> - Koin
> - Coil
> - Ktor
> - Navigation 2(Type Safe)

## Features
### OnBoarding
| On Boarding 1                                                                                                                                                                                                                                 |
|------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------|
|<img width="1280" height="2856" alt="image" src="https://github.com/user-attachments/assets/f0351615-99cd-4202-9f9b-2622c7917d02" />


### Authentication
                                                                                                                          Sign up                                                                                                                                                                                                                                                      |
|------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------|
| <img width="384" height="776" alt="login" src="https://github.com/user-attachments/assets/ba74151f-60b0-4294-8be8-809cc1bde3ca" />![img.png](img.png) | <img width="384" height="776" alt="signup" src="https://github.com/user-attachments/assets/d1b1d4cd-3fcb-4274-bf69-2a78167e1bb8" />![img_1.png](img_1.png)

### Home Screen
| Home                                                                                                                                                                                                                                                                                                                                                                                          |
|------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------|
|  | <img width="384" height="776" alt="home tv shows" src="https://github.com/user-attachments/assets/96c36a18-90b1-47a9-a669-d5343ac283b4" />  |
> [!Note]|

> - **Weakly Progress**: View your workout progress over the week.
> - **Today's Nutrition**:Track your water intake and calorie consumption for the day.
> - **Just For You**:Discover personalized workouts tailored to your goals.


### 🥗 Nutrition
| Nutrition                                                                                                                            | Add Meal                                                                                                                            | Add Water                                                                                                                           | Suggested Meals                                                                                                                              |
|--------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------|
| ![img_2.png](img_2.png) | <img width="384" height="776" alt="search" src="https://github.com/user-attachments/assets/14f28034-222b-403c-8b7c-98461b5b420f" /> | <img width="384" height="776" alt="search results" src="https://github.com/user-attachments/assets/d00dd1d0-f309-4a3f-958c-4c42508d5bf9" /> | <img width="384" height="776" alt="for you" src="https://github.com/user-attachments/assets/cefc37c0-d3ba-47e0-95c4-9040943948a5" /> |
[!Note]
Nutrition: Overview of your daily nutrient and calorie progress.

Add Meal: log your meals with amount of calories.

Add Water: Stay hydrated by recording your daily water intake.

Suggested Meals: meal recommendations.

### Suggested Meals
<table>
  <thead>
    <tr>
      <th>Suggested Meals</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
        <img width="384" height="776" alt="similar movies" src="https://github.com/user-attachments/assets/72c8c7e0-f291-4e49-b6a1-65fde26f2ea6" />
      </td>
    </tr>
  </tbody>
</table>

#### Meal Details:
> **_Displays all the details about the Meal_**
<table>
  <thead>
    <tr>
      <th>Tv Meal details </th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
        <img width="384" height="776" alt="tv show details 1" src="https://github.com/user-attachments/assets/34069c2e-4a62-4596-9ea3-5124d29db77e" />
      </td>
    </tr>
  </tbody>
</table>



### Workout:
|Wokout                                                                                                                           | Create Exercise                                                                                                                            | Community                                                                                                                           | Add Exercise                                                                                                                             | Create Workout                                                                                                                             |
|--------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------|
| <img width="384" height="776" alt="library guest" src="https://github.com/user-attachments/assets/3d824337-3c81-4643-8350-51c59ecdd577" /> | <img width="384" height="776" alt="library logged in" src="https://github.com/user-attachments/assets/16e24d73-b185-4e8b-92d8-8ec00d2fca87" /> | <img width="384" height="776" alt="lists" src="https://github.com/user-attachments/assets/1a02fad9-bacb-4849-be9c-d4a943a4a815" /> | <img width="384" height="776" alt="favorites" src="https://github.com/user-attachments/assets/1277192e-4d8c-4a53-be03-e93f616b9563" /> | <img width="384" height="776" alt="history" src="https://github.com/user-attachments/assets/50438e46-fd06-4abf-9cf4-db8c18c777f1" /> |
> [!Note]
>
> - Displays all of your lists, favorite items and your watch history.
> - Check the lists you have and the items inside of it.
> - Display all of your favorite movies and tv shows.
> - Display your watch history for everything you have watched.




### Profile Screen:
| Profile (Guest)                                                                                                                            | Profile (Logged In)                                                                                                                            | Theme Switch                                                                                                                              | Language Change                                                                                                                              |
|--------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------|
| <img width="384" height="776" alt="profile guest" src="https://github.com/user-attachments/assets/e5b380b2-8acb-4b26-bffb-ac2c5f59f21c" /> | <img width="384" height="776" alt="profile logged in" src="https://github.com/user-attachments/assets/cf490858-d0b4-4c5b-aa79-d31dfd8b0c50" /> | <img width="384" height="776" alt="change theme" src="https://github.com/user-attachments/assets/d96e373a-7f5d-4bc0-949a-f94e8d1cfa73" /> | <img width="384" height="776" alt="change language" src="https://github.com/user-attachments/assets/352b90f3-fa09-4691-b9bb-b1407c95b167" /> |
> [!Note]
>
> - You displays your image and user name.
> - Can change the application theme to light or dark.
> - Can switch easily the language of the application to arabic or english.



### My Ratings:
| Rating Empty                                                                                                                              | Rating filled                                                                                                                              |
|-------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------|
| <img width="384" height="776" alt="rating empty" src="https://github.com/user-attachments/assets/1b254e9e-0e93-4d87-8956-d2477f96d783" /> | <img width="384" height="776" alt="rating filled" src="https://github.com/user-attachments/assets/40206fd6-abc6-4ddc-934e-3b00abc0b5ca" /> |
> [!Note]
>
> - You can easily know what series or movies you have rated.
> - Easily to remove the item you don't want by swiping it.


---
## Installation
1- Clone the Repository
```
git clone https://github.com/Cairo-Squad/EvolveFit.git
```
2- open the project in android studio.
3- Add the required parameters in `local.properties` file.
4- Build and run the project on an emulator or physical device.

## 👥 Contributors
<a href="https://github.com/Cairo-Squad/EvolveFit/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=Cairo-Squad/EvolveFit" />
</a>