/*
 *  Nimble, an extensive application base for Grails
 *  Copyright (C) 2009 Intient Pty Ltd
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
 
import intient.nimble.domain.Role
import intient.nimble.domain.Group
import intient.nimble.domain.Profile
import intient.nimble.domain.Permission

import intient.nimble.service.AdminsService
import intient.nimble.service.UserService

/*
 * Allows applications using Nimble to undertake process at BootStrap that are related to Nimbe provided objects
 * such as Users, Role, Groups, Permissions etc.
 *
 * Utilizing this BootStrap class ensures that the Nimble environment is populated in the backend data repository correctly
 * before the application attempts to make any extenstions.
 */
class NimbleBootStrap {

    def grailsApplication
  
    def nimbleService
    def userService
    def adminsService
    def permissionService

    def init = {servletContext ->

        // The following must be executed
        internalBootStap(servletContext)

        // Execute any custom Nimble related BootStrap for your application below

        // Create example User account
        def user = new User()
        user.username = "jdoe"
        user.pass = 'jdoE123!'
        user.passConfirm = 'jdoE123!'
        user.enabled = true

        Profile userProfile = new Profile()
        userProfile.fullName = "John Doe"
        userProfile.owner = user
        user.profile = userProfile

        def e1 = new Entry(title:"Grails 1.1 beta is out", summary:"Check out the new features")
        def e2 = new Entry(title:"Just Released - Groovy 1.6 beta 2", summary:"It is looking good.")
        user.addToEntries(e1)
        user.addToEntries(e2)

        def savedUser = userService.createUser(user)
        if (savedUser.hasErrors()) {
            savedUser.errors.each {
                log.error(it)
            }
            throw new RuntimeException("Error creating example user jdoe")
        }

        // touch hacky but we need to make sure we have entry ID
        e1.refresh()
        e2.refresh()

        // Allow this user to edit the entires they've created'
        def e1EditPermission = new Permission(type: Permission.wildcardPerm)
        e1EditPermission.target = "blog:entry:*:${e1.id}"
        permissionService.createPermission(e1EditPermission, savedUser)

        def e2EditPermission = new Permission(type: Permission.wildcardPerm)
        e2EditPermission.target = "blog:entry:*:${e2.id}"
        permissionService.createPermission(e2EditPermission, savedUser)

        def user2 = new User()
        user2.username = "jsmith"
        user2.pass = 'jsmitH123!'
        user2.passConfirm = 'jsmitH123!'
        user2.enabled = true

        Profile userProfile2 = new Profile()
        userProfile2.fullName = "John Smith"
        userProfile2.owner = user2
        user2.profile = userProfile

        def e3 = new Entry(title:"Codecs in Grails", summary:"See Mastering Grails")
        def e4 = new Entry(title:"Testing with Groovy", summary:"See Practically Groovy")
        user2.addToEntries(e3)
        user2.addToEntries(e4)

        def savedUser2 = userService.createUser(user2)
        if (savedUser2.hasErrors()) {
            savedUser2.errors.each {
                log.error(it)
            }
            throw new RuntimeException("Error creating example user jsmith")
        }

        // touch hacky but we need to make sure we have entry ID
        e3.refresh()
        e4.refresh()
        
        // Allow this user to edit the entires they've created'
        def e3EditPermission = new Permission(type: Permission.wildcardPerm)
        e3EditPermission.target = "blog:entry:*:${e3.id}"
        permissionService.createPermission(e3EditPermission, savedUser2)

        def e4EditPermission = new Permission(type: Permission.wildcardPerm)
        e4EditPermission.target = "blog:entry:*:${e4.id}"
        permissionService.createPermission(e4EditPermission, savedUser2)



        // Create example Administrative account
        def admins = Role.findByName(AdminsService.ADMIN_ROLE)
        def admin = new User()
        admin.username = "admin"
        admin.pass = "admiN123!"
        admin.passConfirm = "admiN123!"
        admin.enabled = true

        Profile adminProfile = new Profile()
        adminProfile.fullName = "Administrator"
        adminProfile.owner = admin
        admin.profile = adminProfile

        def savedAdmin = userService.createUser(admin)
        if (savedAdmin.hasErrors()) {
            savedAdmin.errors.each {
                log.error(it)
            }
            throw new RuntimeException("Error creating administrator")
        }

        adminsService.add(admin)
    }

    def destroy = {

    }

    private internalBootStap(def servletContext) {
        nimbleService.init()
    }
} 