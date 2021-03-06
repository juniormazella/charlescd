/*
 * Copyright 2020 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import { prepareCircles, getDefaultCircle } from '../helpers';
import { CirclePaginationItem } from 'modules/Circles/interfaces/CirclesPagination';
import { DEPLOYMENT_STATUS } from 'core/enums/DeploymentStatus';

const circles = [
  {
    name: 'Circle',
    deployment: {
      status: DEPLOYMENT_STATUS.deployed,
    }
  },
  {
    name: 'Default',
    deployment: {
      status: DEPLOYMENT_STATUS.deployed,
    }
  }
] as CirclePaginationItem[];

test('must get Default circle', () => {
  const expected = circles[1];
  expect(getDefaultCircle(circles)).toEqual(expected);
});

test('must remove Default circle', () => {
  const expected = [circles[0]];
  expect(prepareCircles(circles)).toEqual(expected);
});
