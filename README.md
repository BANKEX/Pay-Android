# New Project Instruction.


[![build status](https://gitlab.e-legion.com/android/template/badges/master/build.svg)](https://gitlab.e-legion.com/android/template/commits/master)

### Main components
* [Gitlab](https://gitlab.e-legion.com/)
* CI
  * [Gitlab CI](https://gitlab.e-legion.com/ci)
  * [Jenkins](http://jenkins.e-legion.com)
* Crash reporting
  * [Hockey APP](https://rink.hockeyapp.net) 
  * [Crashlytics](https://try.crashlytics.com)
* [Redmine](https://redmine.e-legion.com/projects/android)
* [Sonar](https://sonar.e-legion.com/)

Доступ к каждому ресурсу, обсуждается отдельно с руководителем. Для доступа в HockeyAPP необходимо использовать аккаунт - `services@e-legion.com`

## Step 1: Project based on Template
Преже всего необходимо иметь доступ к новому проеку [GitLab](https://gitlab.e-legion.com). Об этом необходимо уточнить у PM-а, тех лида или лида проекта.

* Вытащить исходники шаблонного проекта и добавить их в новую директорию. Как вариант, можно зайти в Gitlab и нажать на кнопку - `Download ZIP`
* Далее в новой директории выполнить 

```
      git init 
```
* Добавить связь с удаленным репозиторием

```
    git remote add origin git@gitlab.e-legion.com:<PROJECT_NAME>.git
```


* Импортировать проект в `Android Studio` и отредактировать следующие данные:
    * `build.gradle, projectName` - имя проекта, например используется для Sonar в качестве key.
    * `strings.xml`, содержит название проекта (`app_name`) для отображения на UI
    * `package name`, - необходимо корректно изменить имя пакета до загрузки в хоккей, предварительно согласовав с Руководителем/Заказчиком.
    *  Если требуется установить `versionName` для проекта: значение вычисляется в функции `getVersionName`. Значение необходимо задавать по согласованию с Руководителем/Заказчиком. Для сборок в QA используется суффикс получаемый из функции `getQaVersionSuffix`.
    * `README.md`, при необходимости внести описание проекта или инструкцию по его сборке. При желании, данный файл можно удалить. 
    * `app/build.gradle`, содержит минимальную конфигурацию проекта + наиболее используемые библиотеки. По мере стабильности библиотек, версии в шаблоне изменяются.
      * По умолчанию есть 3 buildType: `debug`, `releaseQa` и `release`. Первый для разработки. Второй для релиза в отдел тестирования, подписанный нашим ключом. Третий - неподписанная сборка для заказчика. В крашлитикс неподписанную сборку загрузить нельзя, поэтому необходимо получить ключ у заказчика, либо подписывать нашим ключом.
      * Есть три signingConfig: `debug`, `release` и `playstore`. Для подписи дебажным ключом, нашим ключом и ключом заказчика. Ключ заказчика добавляется на CI тех лидом или руководителем.
    * `proguard-rules.pro`, содержит уже подготовленный набор правил для уже добавленных и для часто используемых библиотек.
* Убедиться, что проект собирается и запускается.
* Добавить необходимые файлы в проект (исключая iml, .gitignore, builds, и тд.) с комментарием `initial commit`
* Запушить изменения на сервер.

## Step 2: CI Configuration

### Jenkins

1. Перейти в `Jenkins`. Переидти в папку со сборками проекта. Создать копию этой папки через Copy Folder.
2. Настроить сборки чтобы они смотрели на корректный репозиторий в Gitlab. Добавить дополнительные шаги сборки при необходимости.
3. Добавить в `Gitlab` `Web hook` с `URL` из конфигурации билдов дженкинса. Для примера можно посмотреть проект шаблона.
    * `release` - собирает релизную сборку в крашлитикс. Тригеры: Push Events, Tag Push Events. Url: https://jenkins.e-legion.com/project/<PROJECT_NAME>/release
    * `releaseQa` - собирает релизную сборку для отдела тестирования в крашлитикс. Тригеры: Push Events, Tag Push Events. Url: https://jenkins.e-legion.com/project/<PROJECT_NAME>/releaseQa
    * `verification` - прогоняет тесты и синтаксические анализаторы при пушах в репозиторий. Тригеры: Push Events. Url: https://jenkins.e-legion.com/project/<PROJECT_NAME>/verification
4. В `Gitlab` включить `Deploy key` `jenkins` в настройках проекта.
5. В `Gitlab` добавить пользователя Jenkins в проект.

Jenkins готов. Далее при необходимости можно добавить новых билдов или изменить конфигурации по-умолчанию. Можно создать pipeline вместо сборок по умолчанию. При необходимости посоветоваться с Тех Лидом.

### Gilab CI
1. Перейти в [GitLab](https://gitlab.e-legion.com). Найти там свой проект.
2. Убедиться что для проекта в настройках CI автоматически добавлены и доступен раннер ubuild01 и другие. Если нет ubuild01 - попросить Тех Лида, Тех Дира или Админа добавить раннер к проекту. Теперь проекты будут собираться на указанном `Runner`.
3. Перейти в настройку переменных окружения и добавить параметр/значение. Не забыть нажать на `Save Changes`
```
    	HOCKEY_APP_TOKEN
    	55a90241ac8c46e6baae96e6f94eae3d
    	REDMINE_URL
	 	https://redmine.e-legion.com
	 	REDMINE_API_KEY
	 	80352e51c41c38850d2d5cf1ac2e51c9c0e611e1
	 	KEYALIAS
	 	elegionandroidmarket
	 	KEYPASS
 	 	apm06bfgy49aket000
 	 	KEYSTORE
 	 	/home/gitlab-runner/android/release.jks
 	 	STOREPASS
 	 	m94n923kad0185zp
    	RELEASE_QA_FABRIC_API_KEY
    	aba301d58d2ab586a56e281861dd14141b362b34
    	RELEASE_QA_FABRIC_API_SECRET
    	a896e777cae983a61bc57b2024bec131e975dce43eebc223bbc343b8f0f46a64
```

4. Дополнительно указывается
```
    	REASSIGN_QA_ID     <----- Перевод тасок на QA в редмайне
    	591     <----- Берем из ссылки https://redmine.e-legion.com/users/591
    	REASSIGN_QA_STATUS_ID     <----- Статус таски после перевода
    	Возможные статусы смотреть [тут](https://gitlab.e-legion.com/snippets/16)
	 	<---- Релизный ключ для сборки проекта---->
	 	RELEASE_KEYALIAS
	 	elegionandroidmarket
	 	RELEASE_KEYPASS
 	 	apm06bfgy49aket000
 	 	RELEASE_KEYSTORE
 	 	/home/gitlab-runner/android/release.jks
 	 	RELEASE_STOREPASS
 	 	m94n923kad0185zp
 	 	<---- Релизные ключи для фабрика---->
    	RELEASE_QA_FABRIC_API_KEY
    	aba301d58d2ab586a56e281861dd14141b362b34
    	RELEASE_QA_FABRIC_API_SECRET
    	a896e777cae983a61bc57b2024bec131e975dce43eebc223bbc343b8f0f46a64
```

5. Добавить и при необходимости настроить файл с конфигурацией `.gitlab-ci.yml` под проект.
Начальный конфиг можно взять [тут](https://gitlab.e-legion.com/snippets/17)
`.gitlab-ci.yml` используется для сборки проектов [Gitlab CI](https://gitlab.e-legion.com/ci).
По умолчанию менять ничего не нужно. В дальнейшем настраивается в зависимости от окружения и нужд проекта. (о том, как настраивать, спросить тех лида).

Gitlab CI готов.

## Step 3: Crash Reporting

### Crashlytics (Fabric)

TBD

### Hockey APP Configuration
Необходимо добавить хоккей в проект и создать сборку в хоккей на CI вместо сборки в крашлитикс. После успешной сборки push в `master`, необходимо запустить `hockey` сборку.

1. Для сборки необходимо на нужном коммите установить `tag` в формате удовлетворяющему `RegExp ^v.*$`, например v1.0.0
2. Новый тэг необходимо запушить в репозиторий `git push --tags`. Тем самым даем команду Gitlab CI на сборку.
3. Убедиться, что CI подхватил нужный `Tag` и выполнил сборку.
4. Если сборка по какой-то причине не выполнилась, тэг нужно удалить и повторить попытку с тем же тэгом еще раз (не забыть про то, что удалять нужно и с CI раннера).
5. После успешной сборки, собранная `APK` + `Proguard Mapping` + `Release notes`, отправляются в [Hockey APP](https://rink.hockeyapp.net).
6. `Release Notes` (см скрипт /usr/bin/changelog в настройках) собираются по коммитам (коммиты вида #XXXX: между текущим и последним тэгами) и redmine (Fixed таски).
7. В [Hockey APP](https://rink.hockeyapp.net)  создается новый проект.
8. Необходимо скопировать `App ID`: и сохранить его в переменной проекта (`build.gradle, hockeyAppId`) и использовать в проекте, где необходимо
9. В [Hockey APP](https://rink.hockeyapp.net), добавить разработчиков и руководителя проекта. Параметры этим пользователям: 

```
        Role=developer; Tags=test.
```

## Step 4: Sonar
На данный момент не используется. TBD: включить на проектах.

После успешной сборки (в [Gitlab CI](https://gitlab.e-legion.com/ci), hash нужного коммита должен быть зеленого цвета). Приложение автоматически доступно для мониторинга качества кода [Sonar](https://sonar.e-legion.com/).

Для доступа к ресурсу используется логин/пароль от облачного хранилища (если что к админу)

Изначально, проект настроен идеально, никаких issues не создается. При каждом push в `master` ветку, будет собираться сборка и исходные коды прогоняться через Sonar. Необходимо следить за чистотой и качеством кода!

### Instruction result
По завершеню выполнения инструкции, на проекте будут следующие возможности:
* Исходный проект из Шаблона в Gitlab
* Сборки в CI
* Возможность собирать Release Notes, основываясь на redmine + комитах.
* Деплоить сборки и собирать Crash репорты
* Проводить анализ качества кода с помощью Sonar.
* Иметь сквозную нумерацию билдов `VersionCode`.