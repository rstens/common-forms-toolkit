import { createLocalVue, shallowMount } from '@vue/test-utils';
import Vuex from 'vuex';

import AdminNavBar from '@/components/common/admin/AdminNavBar.vue';
import getRouter from '@/router';

const router = getRouter();
const localVue = createLocalVue();
localVue.use(Vuex);

describe('AdminNavBar.vue', () => {
  const formName = 'testForm';
  const resource = `comfort-${formName}`;
  let store;

  beforeEach(() => {
    store = new Vuex.Store();
  });

  it('renders nothing when not on admin page', async () => {
    const wrapper = shallowMount(AdminNavBar, {
      localVue,
      propsData: { formName: formName, resource: resource },
      router,
      stubs: ['router-link']
    });

    expect(wrapper.text()).toBeFalsy();
  });

  it('isAdmin can determine if user is an administrator', async () => {
    store.registerModule('auth', {
      namespaced: true,
      getters: {
        hasResourceRoles: () => () => true
      }
    });

    const wrapper = shallowMount(AdminNavBar, {
      localVue,
      propsData: { formName: formName, resource: resource },
      router,
      store,
      stubs: ['router-link']
    });

    expect(wrapper.vm.isAdmin).toBeTruthy();
  });
});
