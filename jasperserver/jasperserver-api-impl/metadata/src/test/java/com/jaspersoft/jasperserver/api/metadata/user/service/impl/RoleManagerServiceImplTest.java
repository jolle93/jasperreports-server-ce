/*
 * Copyright (C) 2005 - 2020 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.jaspersoft.jasperserver.api.metadata.user.service.impl;

import com.jaspersoft.jasperserver.api.metadata.user.domain.Role;
import com.jaspersoft.jasperserver.api.metadata.user.domain.User;
import com.jaspersoft.jasperserver.api.metadata.user.domain.client.RoleImpl;
import com.jaspersoft.jasperserver.api.metadata.user.domain.client.UserImpl;
import com.jaspersoft.jasperserver.api.metadata.user.service.UserAuthorityService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 */
@RunWith(MockitoJUnitRunner.class)
public class RoleManagerServiceImplTest {

    @Mock
    private UserAuthorityService userService;
    @Mock
    private AbstractPlatformTransactionManager transactionManager;

    @InjectMocks
    private RoleManagerServiceImpl managerService;

    String demo;
    String first;
    String second;

    String bob;
    String steve;
    String bill;
    String jon;

    User bobUser;
    User steveUser;
    User billUser;
    User jonUser;

    Role demoRole;
    Role firstRole;
    Role secondRole;

    @Before
    public void before() {
        demo = "demo";
        first = "first";
        second = "second";

        bob = "bob";
        steve = "steve";
        bill = "bill";
        jon = "jon";

        bobUser = new UserImpl();
        bobUser.setUsername(bob);

        steveUser = new UserImpl();
        steveUser.setUsername(steve);

        billUser = new UserImpl();
        billUser.setUsername(bill);

        jonUser = new UserImpl();
        jonUser.setUsername(jon);

        demoRole = new RoleImpl();
        demoRole.setRoleName(demo);

        firstRole = new RoleImpl();
        firstRole.setRoleName(first);

        secondRole = new RoleImpl();
        secondRole.setRoleName(second);

        reset(userService);
    }

    @After
    public void after() {
        verifyNoMoreInteractions(userService, transactionManager);
    }

    @Test
    public void deleteAll() {
        List<String> roles = new ArrayList<String>();
        roles.add(demo);
        roles.add(first);
        roles.add(second);

        managerService.deleteAll(null, roles);

        verify(userService, times(1)).deleteRole(null, demo);
        verify(userService, times(1)).deleteRole(null, first);
        verify(userService, times(1)).deleteRole(null, second);
    }

    @Test
    public void deleteAllIfNull() {
        managerService.deleteAll(null, null);
    }

    @Test
    public void deleteAllIfEmptyList() {
        managerService.deleteAll(null, new ArrayList<String>());
    }

    @Test
    public void deleteAllIfListWithNullAndEmptyValue() {
        List<String> roles = new ArrayList<String>();
        roles.add(null);
        roles.add("");

        managerService.deleteAll(null, roles);
    }

    @Test
    public void updateRole() {
        Set assignedUserNameSet = new HashSet();
        Set unassignedUserNameSet = new HashSet();

        assignedUserNameSet.add(bob);
        assignedUserNameSet.add(steve);

        unassignedUserNameSet.add(bill);
        unassignedUserNameSet.add(jon);

        Set<User> assignedUserSet = new HashSet<User>();
        Set<User> unassignedUserSet = new HashSet<User>();

        assignedUserSet.add(bobUser);
        assignedUserSet.add(steveUser);

        unassignedUserSet.add(billUser);
        unassignedUserSet.add(jonUser);

        managerService.updateRole(null, demo, demoRole, assignedUserSet, unassignedUserSet);

        verify(userService, times(1)).assignUsers(null, demo, assignedUserNameSet);
        verify(userService, times(1)).unassignUsers(null, demo, unassignedUserNameSet);
        verify(userService , times(1)).updateRole(null, demo, demoRole);
    }

}