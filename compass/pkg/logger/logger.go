/*
 *
 *  Copyright 2020 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package logger

import (
	"github.com/ZupIT/charlescd/compass/internal/configuration"
	"github.com/sirupsen/logrus"
	"time"
)

func Info(msg string, data interface{}) {
	if configuration.GetConfiguration("ENV") == "TEST" {
		return
	}

	logrus.WithFields(logrus.Fields{
		"Data": data,
	}).Infoln(msg)
}

func Error(msg string, functionName string, err error, data interface{}) {
	if configuration.GetConfiguration("ENV") == "TEST" {
		return
	}

	logrus.WithFields(logrus.Fields{
		"Error":        err,
		"FunctionName": functionName,
		"Data":         data,
	}).WithTime(time.Now()).Errorln(msg)
}
func Panic(msg string, functionName string, err error, data interface{}) {
	if configuration.GetConfiguration("ENV") == "TEST" {
		return
	}

	logrus.WithFields(logrus.Fields{
		"Error":        err,
		"FunctionName": functionName,
		"Data":         data,
	}).WithTime(time.Now()).Panicln(msg)
}

func Fatal(msg string, err error) {
	logrus.Fatalln(msg, err)
}
